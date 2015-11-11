package be.kdg.schelderadarchain.processor.model.mapping;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import be.kdg.schelderadarchain.processor.buffer.dto.ShipServiceCargo;
import be.kdg.schelderadarchain.processor.buffer.dto.ShipServiceShip;
import be.kdg.schelderadarchain.processor.model.Cargo;
import be.kdg.schelderadarchain.processor.model.Incident;
import be.kdg.schelderadarchain.processor.model.Position;
import be.kdg.schelderadarchain.processor.model.Ship;
import be.kdg.schelderadarchain.processor.utility.StringUtils;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import be.kdg.schelderadarchain.processor.amqp.dto.AMQPMessage;

/**
 * Created by Olivier on 09/11/2015.
 */
public final class ModelMapper {
    public static Position mapAmqpToPosition(AMQPMessage amqpMessage) {
        Position message;
        Reader reader = new StringReader(amqpMessage.getMessage());

        try {
            message = (Position) Unmarshaller.unmarshal(Position.class, reader);
        } catch (MarshalException e) {
            // lol
            return null;
        } catch (ValidationException e) {
            // lol
            return null;
        }

        return message;
    }

    public static Position map(AMQPMessage amqpMessage) {
        Position message;
        Reader reader = new StringReader(amqpMessage.getMessage());

        try {
            message = (Position) Unmarshaller.unmarshal(Position.class, reader);
        } catch (MarshalException e) {
            // lol
            return null;
        } catch (ValidationException e) {
            // lol
            return null;
        }

        return message;
    }

    public static Incident mapAmqpToIncident(AMQPMessage amqpMessage) {
        Incident message;
        Reader reader = new StringReader(amqpMessage.getMessage());

        try {
            message = (Incident) Unmarshaller.unmarshal(Incident.class, reader);
        } catch (MarshalException e) {
            // lol
            return null;
        } catch (ValidationException e) {
            // lol
            return null;
        }

        return message;
    }

    public static Ship map(ShipServiceShip msg) {
        ArrayList<Cargo> cargo = new ArrayList<>();

        // map all ShipServiceCargo elements to Cargo
        msg.getCargo().forEach((shipServiceCargo) -> cargo.add(map(shipServiceCargo)));

        // manipulate IMO string to Processors standards
        String imo = StringUtils.slice(3, msg.getIMO());

        return new Ship(Integer.parseInt(imo), msg.getNumberOfPassangers(), msg.getDangereousCargo(), cargo);
    }

    public static Cargo map(ShipServiceCargo cargo) {
        return new Cargo(cargo.getAmount(), cargo.getType());
    }
}
