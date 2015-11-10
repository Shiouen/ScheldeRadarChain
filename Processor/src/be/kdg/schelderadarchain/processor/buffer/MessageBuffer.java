package be.kdg.schelderadarchain.processor.buffer;

import java.util.ArrayList;

import be.kdg.schelderadarchain.processor.buffer.schedule.BufferScheduleFactory;
import be.kdg.schelderadarchain.processor.model.Message;

/**
 * Created by Olivier on 04-Nov-15.
 */
public class MessageBuffer extends Buffer<Integer, Message> {
    public MessageBuffer() {
        super();
    }

    @Override
    public void buffer(Message message) {
        Integer shipId = message.getShipId();

        if (this.buffer.get(shipId) == null) {
            this.buffer.put(shipId, new ArrayList<>());
        }

        this.buffer.get(shipId).add(message);

        BufferScheduleFactory.schedule(this, shipId);
    }

}
