package be.kdg.schelderadarchain.processor.buffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.kdg.schelderadarchain.processor.buffer.adapter.ShipServiceReceiver;
import be.kdg.schelderadarchain.processor.buffer.properties.BufferProperties;
import be.kdg.schelderadarchain.processor.buffer.schedule.BufferScheduleFactory;
import be.kdg.schelderadarchain.processor.model.Ship;
import be.kdg.schelderadarchain.processor.model.Message;

/**
 * Created by Olivier on 04-Nov-15.
 */
public class MessageBuffer implements Buffer<Integer, Message> {
    private HashMap<Integer, ArrayList<Message>> buffer;
    private BufferScheduleFactory scheduleFactory;
    private ShipCache cache;

    public MessageBuffer(ShipCache shipCache) {
        this.buffer = new HashMap<>();
        this.scheduleFactory = new BufferScheduleFactory();
        this.cache = shipCache;
    }

    public List<Message> getMessages(Integer shipId) {
        return this.buffer.get(shipId);
    }

    @Override
    public boolean isBuffered(Integer shipId) {
        return !this.buffer.get(shipId).isEmpty();
    }

    @Override
    public void buffer(Message message) {
        Integer shipId = message.getShipId();

        // if no messages init new list of messages for new ship
        if (this.getMessages(shipId) == null) {
            this.buffer.put(shipId, new ArrayList<>());
        }

        // if no messages buffered
        if (!this.isBuffered(shipId)) {
            // if no info message cached
            if (!this.cache.isBuffered(shipId)) {
                ShipServiceReceiver receiver = new ShipServiceReceiver();
                Ship ship = receiver.receive(shipId);

                if (receiver.getSuccess()) {
                    this.cache.buffer(ship);
                }
            }
        }

        this.buffer.get(shipId).add(message);
        this.scheduleFactory.schedule(this, shipId, BufferProperties.getMessageBufferDuration());
    }

    @Override
    public void clear(Integer key) {
        this.buffer.get(key).clear();
    }
}
