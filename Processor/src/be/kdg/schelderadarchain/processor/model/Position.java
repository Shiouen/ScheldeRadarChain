package be.kdg.schelderadarchain.processor.model;

import be.kdg.schelderadarchain.processor.amqp.dto.AMQPMessage;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import java.io.Reader;
import java.io.StringReader;
import java.sql.Timestamp;

/**
 * Created by Olivier on 09/11/2015.
 */
public class Position extends Message {
    private int distanceToLoadingDock;
    private String stationId;
    private Timestamp timestamp;

    public Position() { }

    public int getDistanceToLoadingDock() { return this.distanceToLoadingDock; }
    public String getStationId() { return this.stationId; }
    public Timestamp getTimestamp() { return this.timestamp; }

    public void setDistanceToLoadingDock(int distanceToLoadingDock) {
        this.distanceToLoadingDock = distanceToLoadingDock;
    }
    public void setStationId(String stationId) { this.stationId = stationId; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        String s = "Position {\n\tshipId : '%s'\n\tdistanceToLoadingDock : '%s'\n\tstationId : '%s'\n\ttimestamp : '%s'\n};";
        return String.format(s, this.shipId, this.distanceToLoadingDock, this.stationId, this.timestamp);
    }
}
