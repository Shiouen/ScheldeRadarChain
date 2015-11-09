package be.kdg.schelderadarchain.processor.controller;

import java.io.Reader;
import java.io.StringReader;

import be.kdg.schelderadarchain.processor.model.IncidentMessage;
import be.kdg.schelderadarchain.processor.model.PositionMessage;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import be.kdg.schelderadarchain.processor.amqp.dto.AMQPMessage;

/**
 * Created by Olivier on 09/11/2015.
 */
public final class ModelMapper {
    public static PositionMessage mapAmqpToPosition(AMQPMessage amqpMessage) {
        PositionMessage message;
        Reader reader = new StringReader(amqpMessage.getMessage());

        try {
            message = (PositionMessage) Unmarshaller.unmarshal(PositionMessage.class, reader);
        } catch (MarshalException e) {
            // lol
            return null;
        } catch (ValidationException e) {
            // lol
            return null;
        }

        return message;
    }

    public static IncidentMessage mapAmqpToIncident(AMQPMessage amqpMessage) {
        IncidentMessage message;
        Reader reader = new StringReader(amqpMessage.getMessage());

        try {
            message = (IncidentMessage) Unmarshaller.unmarshal(IncidentMessage.class, reader);
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
