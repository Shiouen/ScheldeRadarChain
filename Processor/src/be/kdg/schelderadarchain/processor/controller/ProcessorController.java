package be.kdg.schelderadarchain.processor.controller;

import org.apache.log4j.Logger;

import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;
import be.kdg.schelderadarchain.processor.amqp.properties.RabbitMQProperties;
import be.kdg.schelderadarchain.processor.buffer.ShipCache;
import be.kdg.schelderadarchain.processor.buffer.MessageBuffer;

/**
 * This class acts as a starting point for the application.
 *
 * @author Olivier Van Aken
 */
public class ProcessorController {
    private IncidentController incidentController;
    private PositionController positionController;

    private MessageBuffer messageBuffer;
    private ShipCache shipCache;

    private final static Logger logger = Logger.getLogger(ProcessorController.class);

    public ProcessorController() {
        this.shipCache = new ShipCache();
        this.messageBuffer = new MessageBuffer(this.shipCache);

        // incident queue receiver
        String host = RabbitMQProperties.getHost();
        String queue = RabbitMQProperties.getReceiverIncidentQueue();
        this.incidentController = new IncidentController(host, queue, this.messageBuffer);

        // position queue receiver
        host = RabbitMQProperties.getHost();
        queue = RabbitMQProperties.getReceiverPositionQueue();
        this.positionController = new PositionController(host, queue, this.messageBuffer);
    }

    public void connectCommunicators() {
        try {
            this.incidentController.startReceiver();
            this.positionController.startReceiver();
        } catch (AMQPException e) {
            logger.fatal(e.getMessage());
            System.exit(1);
        }
    }

    public void disconnectCommunicators() {
        try {
            this.incidentController.stopReceiver();
            this.positionController.stopReceiver();
        } catch (AMQPException e) {
            logger.fatal(e.getMessage());
            System.exit(1);
        }
    }
}
