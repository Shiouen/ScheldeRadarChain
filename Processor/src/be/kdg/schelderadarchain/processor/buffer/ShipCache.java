package be.kdg.schelderadarchain.processor.buffer;

import java.util.HashMap;

import be.kdg.schelderadarchain.processor.buffer.properties.BufferProperties;
import be.kdg.schelderadarchain.processor.buffer.schedule.BufferScheduleFactory;
import be.kdg.schelderadarchain.processor.model.Ship;

/**
 * Created by Olivier on 10-Nov-15.
 */
public class ShipCache implements Buffer<Integer, Ship> {
    private HashMap<Integer, Ship> cache;
    private BufferScheduleFactory scheduleFactory;

    public ShipCache() {
        this.cache = new HashMap<>();
        this.scheduleFactory = new BufferScheduleFactory();
    }

    public Ship getShip(Integer shipId) {
        return this.cache.get(shipId);
    }

    @Override
    public boolean isBuffered(Integer shipId) {
        return this.getShip(shipId) != null;
    }

    @Override
    public void buffer(Ship message) {
        Integer shipId = message.getShipId();

        this.cache.put(shipId, message);

        this.scheduleFactory.schedule(this, shipId, BufferProperties.getInfoMessageBufferDuration());
    }

    @Override
    public void clear(Integer shipId) {
        this.cache.put(shipId, null);
    }
}
