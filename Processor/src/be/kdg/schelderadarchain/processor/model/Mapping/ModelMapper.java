package be.kdg.schelderadarchain.processor.model.mapping;

import java.time.LocalDateTime;
import java.util.ArrayList;

import be.kdg.schelderadarchain.processor.amqp.dto.*;
import be.kdg.schelderadarchain.processor.buffer.dto.*;
import be.kdg.schelderadarchain.processor.model.*;
import be.kdg.schelderadarchain.processor.utility.StringUtils;

/**
 * This class provides model mapping throughout the processor.
 *
 * @author Olivier Van Aken
 */
public final class ModelMapper {
    public static Incident map(AMQPIncident amqpIncident) {
        int shipId = amqpIncident.getShipId();
        String incidentType = amqpIncident.getIncidentType();

        return new Incident(shipId, incidentType);
    }

    public static Position map(AMQPPosition amqpPosition) {
        int shipId = amqpPosition.getShipId();
        int distance = amqpPosition.getDistanceToLoadingDock();
        String stationId = amqpPosition.getStationId();
        LocalDateTime timestamp = amqpPosition.getTimestamp().toLocalDateTime();

        return new Position(shipId, distance, stationId, timestamp);
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
