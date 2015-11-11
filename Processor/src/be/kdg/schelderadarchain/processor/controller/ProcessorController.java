package be.kdg.schelderadarchain.processor.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private IncidentController incidentController;
    private PositionController positionController;

    private MessageBuffer messageBuffer;
    private ShipCache shipCache;

    public ProcessorController() {
        RabbitMQReceiver receiver;

        this.shipCache = new ShipCache();
        this.messageBuffer = new MessageBuffer(this.shipCache);

        try {
            // incident queue receiver
            receiver = new RabbitMQReceiver(RabbitMQProperties.getHost(), RabbitMQProperties.getReceiverIncidentQueue());
            this.incidentController = new IncidentController(receiver, this.messageBuffer);

            // position queue receiver
            receiver = new RabbitMQReceiver(RabbitMQProperties.getHost(), RabbitMQProperties.getReceiverPositionQueue());
            this.positionController = new PositionController(receiver, this.messageBuffer);
        } catch (AMQPReceiverException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void connectCommunicators() {
        this.incidentController.startReceiving();
        this.positionController.startReceiving();
    }

    public void disconnectCommunicators() {
        this.incidentController.stopReceiving();
        this.positionController.stopReceiving();
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
