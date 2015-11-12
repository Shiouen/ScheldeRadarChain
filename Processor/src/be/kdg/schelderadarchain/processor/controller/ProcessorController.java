package be.kdg.schelderadarchain.processor.controller;

import java.util.Scanner;

import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;
import be.kdg.schelderadarchain.processor.amqp.properties.RabbitMQProperties;
import be.kdg.schelderadarchain.processor.buffer.ShipCache;
import be.kdg.schelderadarchain.processor.buffer.MessageBuffer;

/**
 * This class acts as a starting point for the application.
 */
public class ProcessorController {
    private IncidentController incidentController;
    private PositionController positionController;

    private MessageBuffer messageBuffer;
    private ShipCache shipCache;

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
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void disconnectCommunicators() {
        try {
            this.incidentController.stopReceiver();
            this.positionController.stopReceiver();
        } catch (AMQPException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        ProcessorController processorController = new ProcessorController();
        processorController.connectCommunicators();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press any key to exit.");
        scanner.nextLine();

        processorController.disconnectCommunicators();
    }
}
