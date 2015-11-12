package be.kdg.schelderadarchain.generator.backend.generator;

import be.kdg.schelderadarchain.generator.amqp.adapter.RabbitMQSender;
import be.kdg.schelderadarchain.generator.amqp.properties.RabbitMQProperties;
import be.kdg.schelderadarchain.generator.backend.dom.IncidentMessage;
import be.kdg.schelderadarchain.generator.backend.dom.IncidentStatusMessage;
import be.kdg.schelderadarchain.generator.backend.dom.PositionMessage;
import be.kdg.schelderadarchain.generator.backend.dom.Route;
import be.kdg.schelderadarchain.generator.backend.utility.XmlConverter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Cas on 9/11/2015.
 */
public class RandomLoadGenerator extends BaseGenerator {

    private final int MIN_SHIP_ID = 1000000;
    private final int MAX_SHIP_ID = 9999999;
    private final int MAX_DISTANCE_TO_LOADING_DOCK = 80000;
    private final int NUMBER_OF_POSITION_MESSAGES = 30;
    private final int NUMBER_OF_ROUTES = 10;
    private final int NO_INCIDENT_FREQUENCY = 101;
    private final int FIRST_INDEX = 0;
    private final String FREQUENCY = "3000";

    public RandomLoadGenerator() {
        super();
    }

    @Override
    public void start() {
        for (int i = 0; i < this.NUMBER_OF_ROUTES; i++) {
            List<PositionMessage> positionMessages = new ArrayList<>();
            for (int j = 0; j < this.NUMBER_OF_POSITION_MESSAGES; j++) {
                PositionMessage positionMessage = new PositionMessage(this.FREQUENCY);
                positionMessages.add(positionMessage);
            }
            Route route = new Route(positionMessages.get(this.FIRST_INDEX).getShipId(), positionMessages);
            RouteLoop loop = new RouteLoop(route, this, this.NO_INCIDENT_FREQUENCY);
            loops.add(loop);
            Thread thread = new Thread(loop);
            thread.setUncaughtExceptionHandler(this);
            thread.start();
        }
    }

    @Override
    public void sendPositionMessage(PositionMessage message) {
        String xmlMessage = XmlConverter.toXml(message);
        this.sender = new RabbitMQSender(RabbitMQProperties.getHost(), RabbitMQProperties.getSenderPositionMessageQueue(), xmlMessage);
        this.sender.open();
        this.sender.close();
    }

    @Override
    public void sendIncident(IncidentMessage incidentMessage) {

    }

    @Override
    public IncidentMessage receiveIncident(String shipId) {
        return null;
    }

    @Override
    public IncidentStatusMessage receiveIncidentStatus(String shipId) {
        return null;
    }
}
