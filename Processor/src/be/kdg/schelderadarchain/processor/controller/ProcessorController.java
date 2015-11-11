package be.kdg.schelderadarchain.processor.controller;

import java.util.Scanner;

import be.kdg.schelderadarchain.processor.amqp.adapter.rabbitmq.RabbitMQReceiver;
import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPReceiverException;
import be.kdg.schelderadarchain.processor.amqp.properties.RabbitMQProperties;
import be.kdg.schelderadarchain.processor.buffer.ShipCache;
import be.kdg.schelderadarchain.processor.buffer.MessageBuffer;

/**
 * Created by Olivier on 03-Nov-15.
 */
public class ProcessorController {
    private IncidentMessageController incidentMessageController;
    private PositionMessageController positionMessageController;

    private MessageBuffer messageBuffer;
    private ShipCache shipCache;

    public ProcessorController() {
        RabbitMQReceiver receiver;

        this.shipCache = new ShipCache();
        this.messageBuffer = new MessageBuffer(this.shipCache);

        try {
            // incident queue receiver
            receiver = new RabbitMQReceiver(RabbitMQProperties.getHost(), RabbitMQProperties.getReceiverIncidentQueue());
            this.incidentMessageController= new IncidentMessageController(receiver, this.messageBuffer);

            // position queue receiver
            receiver = new RabbitMQReceiver(RabbitMQProperties.getHost(), RabbitMQProperties.getReceiverPositionQueue());
            this.positionMessageController = new PositionMessageController(receiver, this.messageBuffer);
        } catch (AMQPReceiverException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void connectCommunicators() {
        this.incidentMessageController.startReceiving();
        this.positionMessageController.startReceiving();
    }

    public void disconnectCommunicators() {
        this.incidentMessageController.stopReceiving();
        this.positionMessageController.stopReceiving();
    }

    public static void main(String[] args) {
        ProcessorController processorController = new ProcessorController();
        processorController.connectCommunicators();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Press any key to exit.");
        scanner.nextLine();

        processorController.disconnectCommunicators();
    }
}
