package be.kdg.schelderadarchain.processor.model;

import java.io.Reader;
import java.io.StringReader;
import java.sql.Timestamp;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import be.kdg.schelderadarchain.processor.amqp.model.AMQPMessage;

public class Message {
    private int distanceToLoadingDock;
    private int shipId;
    private MessageType messageType;
    private String stationId;
    private Timestamp timestamp;

    public Message() { }

    public int getDistanceToLoadingDock() { return this.distanceToLoadingDock; }
    public MessageType getMessageType() { return messageType; }
    public int getShipId() { return this.shipId; }
    public String getStationId() { return this.stationId; }
    public Timestamp getTimestamp() { return this.timestamp; }

    public void setDistanceToLoadingDock(int distanceToLoadingDock) {
        this.distanceToLoadingDock = distanceToLoadingDock;
    }
    public void setMessageType(MessageType messageType) { this.messageType = messageType; }
    public void setShipId(int shipId) { this.shipId = shipId; }
    public void setStationId(String stationId) { this.stationId = stationId; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    public static Message unmarshal(AMQPMessage amqpMessage) {
        Message message;
        Reader reader = new StringReader(amqpMessage.getMessage());

        try {
            message = (Message) Unmarshaller.unmarshal(Message.class, reader);
        } catch (MarshalException e) {
            // lol
            return null;
        } catch (ValidationException e) {
            // lol
            return null;
        }

        return message;
    }
}
