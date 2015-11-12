package be.kdg.schelderadarchain.generator.backend.generator;

import be.kdg.schelderadarchain.generator.amqp.adapter.AMQPCommunicator;
import be.kdg.schelderadarchain.generator.amqp.adapter.RabbitMQReceiver;
import be.kdg.schelderadarchain.generator.amqp.adapter.RabbitMQSender;
import be.kdg.schelderadarchain.generator.amqp.properties.RabbitMQProperties;
import be.kdg.schelderadarchain.generator.backend.dom.*;
import be.kdg.schelderadarchain.generator.backend.dto.IncidentMessageDTO;
import be.kdg.schelderadarchain.generator.backend.dto.IncidentStatusMessageDTO;
import be.kdg.schelderadarchain.generator.backend.utility.JsonConverter;
import be.kdg.schelderadarchain.generator.backend.utility.RouteFileReader;
import be.kdg.schelderadarchain.generator.backend.utility.XmlConverter;
import be.kdg.se3.proxy.IncidentServiceProxy;

import java.io.IOException;

/**
 * This class is the main engine, it extends the BaseGenerator class.
 * Starts route loops for each route file, who each run in their own thread.
 *
 * @author Cas Decelle
 */

public class SimulationGenerator extends BaseGenerator implements ActionReportListener {

    private final int INCIDENT_FREQUENCY = 100;     //in % chance per position message
    private final String SEPARATOR = ";";
    private final String INCIDENT_URL = "www.services4se3.com/incidentservice/simulate/";
    private final String STATUS_URL = "www.services4se3.com/incidentservice/status/";
    private final String ROUTEFILE_FOLDER = "routes";
    private final String ALL_SHIPS_STATIONARY = "AlleSchepenVoorAnker";
    private final String ALL_SHIPS_IN_ZONE_STATIONARY = "AlleSchepenInZoneVoorAnker";
    private AMQPCommunicator receiver;


    @Override
    public void start() {
        this.receiver = new RabbitMQReceiver(RabbitMQProperties.getHost(), RabbitMQProperties.getReceiverIncidentActionReportQueue(), this);
        this.routes.addAll(RouteFileReader.readRouteFilesFromCsv(ROUTEFILE_FOLDER, this.SEPARATOR));

        for(Route route : routes){
            RouteLoop loop = new RouteLoop(route, this, this.INCIDENT_FREQUENCY);
            loops.add(loop);
            Thread thread = new Thread(loop);
            thread.setUncaughtExceptionHandler(this);
            thread.start();
        }
    }

    @Override
    public IncidentMessage receiveIncident(String shipId){
        try {
            String incident = new IncidentServiceProxy().get(this.INCIDENT_URL + shipId);
            IncidentMessageDTO incidentMessageDTO = JsonConverter.fromJson(incident, IncidentMessageDTO.class);
            IncidentMessage incidentMessage = new IncidentMessage(incidentMessageDTO);
            return incidentMessage;

        } catch (IOException e) {
            String message = "Unexpected error in incident service proxy";
            this.logger.error(message);
            throw new GeneratorException(message, e);
        }
    }

    @Override
    public IncidentStatusMessage receiveIncidentStatus(String shipId) {
        try {
            String status = new IncidentServiceProxy().get(this.STATUS_URL + shipId);
            IncidentStatusMessageDTO incidentStatusMessageDTO = JsonConverter.fromJson(status, IncidentStatusMessageDTO.class);
            IncidentStatusMessage incidentStatusMessage = new IncidentStatusMessage(incidentStatusMessageDTO);
            return incidentStatusMessage;

        } catch (IOException e) {
            String message = "Unexpected error in incident service proxy";
            this.logger.error(message);
            throw new GeneratorException(message, e);
        }
    }

    @Override
    public void sendIncident(IncidentMessage message) {
        String xmlMessage = XmlConverter.toXml(message);
        this.sender = new RabbitMQSender(RabbitMQProperties.getHost(), RabbitMQProperties.getSenderIncidentQueue(), xmlMessage);
        this.sender.open();
        this.sender.close();
    }

    @Override
    public void sendPositionMessage(PositionMessage message) {
        String xmlMessage = XmlConverter.toXml(message);
        this.sender = new RabbitMQSender(RabbitMQProperties.getHost(), RabbitMQProperties.getSenderPositionMessageQueue(), xmlMessage);
        this.sender.open();
        this.sender.close();
    }

    @Override
    public void onActionReportReceived(ActionReport actionReport) {
        String type = actionReport.getType();
        for (RouteLoop loop : loops){
            if (type.equalsIgnoreCase(this.ALL_SHIPS_STATIONARY)) loop.setStationary(true);
            if (type.equalsIgnoreCase(this.ALL_SHIPS_IN_ZONE_STATIONARY)) loop.setStationary(true, actionReport.getShipId());
        }
    }
}


