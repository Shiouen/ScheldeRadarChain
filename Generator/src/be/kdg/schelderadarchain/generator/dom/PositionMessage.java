package be.kdg.schelderadarchain.generator.dom;

import java.util.Date;

/**
 * Created by Cas on 3/11/2015.
 */
public class PositionMessage {

    private String shipId;
    private String delay;
    private String stationId;
    private Date timestamp;
    private String distanceToLoadingDock;

    public PositionMessage() {
    }

    public PositionMessage(String shipId, String delay, String stationId, String distanceToLoadingDock) {
        this.shipId = shipId;
        this.delay = delay;
        this.stationId = stationId;
        this.distanceToLoadingDock = distanceToLoadingDock;
        this.timestamp = new Date();
    }

    public String getShipId() {
        return this.shipId;
    }

    public String getStationId() {
        return this.stationId;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getDistanceToLoadingDock() {
        return this.distanceToLoadingDock;
    }

    public String getDelay() {
        return this.delay;
    }
}
