package be.kdg.schelderadarchain.processor.amqp.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This DTO class represents a Position within the AMQP part of the processor.
 *
 * @author Olivier Van Aken
 */
public class AMQPPosition {
    private int shipId;
    private int distanceToLoadingDock;
    private String stationId;
    private Timestamp timestamp;

    private final Class typeParameter;

    public AMQPPosition() {
        this.typeParameter = this.getClass();
    }

    public int getShipId() { return this.shipId; }
    public int getDistanceToLoadingDock() { return this.distanceToLoadingDock; }
    public String getStationId() { return this.stationId; }
    public Timestamp getTimestamp() { return this.timestamp; }


    public void setShipId(int shipId) { this.shipId = shipId; }
    public void setDistanceToLoadingDock(int distanceToLoadingDock) { this.distanceToLoadingDock = distanceToLoadingDock; }
    public void setStationId(String stationId) { this.stationId = stationId; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        String s = "AMQPPosition {\n\tshipId : '%s'\n\tdistanceToLoadingDock : '%s'\n\tstationId : '%s'\n\ttimestamp : '%s'\n};";
        return String.format(s, this.shipId, this.distanceToLoadingDock, this.stationId, this.timestamp);
    }
}
