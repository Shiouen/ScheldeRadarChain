package be.kdg.schelderadarchain.processor.buffer;

import java.util.HashMap;

import org.apache.log4j.Logger;

import be.kdg.schelderadarchain.processor.buffer.properties.BufferProperties;
import be.kdg.schelderadarchain.processor.buffer.schedule.BufferScheduleFactory;
import be.kdg.schelderadarchain.processor.model.Ship;

/**
 * This class is a Buffer<Integer, Ship> implementation that makes use of a HashMap containing keys towards Ships.
 *
 * Its Ships are kept temporarily using BufferSchedules.
 */
public class ShipCache implements Buffer<Integer, Ship> {
    private HashMap<Integer, Ship> cache;
    private BufferScheduleFactory scheduleFactory;

    private final static Logger logger = Logger.getLogger(ShipCache.class);

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
        this.logger.info(String.format("Caching Ship %s information", shipId));

        this.scheduleFactory.schedule(this, shipId, BufferProperties.getInfoMessageBufferDuration());
    }

    @Override
    public void clear(Integer shipId) {
        this.cache.put(shipId, null);
        this.logger.info(String.format("Clearing Ship %s cached information", shipId));
    }
}
