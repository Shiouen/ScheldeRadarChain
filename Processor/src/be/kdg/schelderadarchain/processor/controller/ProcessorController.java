package be.kdg.schelderadarchain.processor.controller;

import java.util.Scanner;

import be.kdg.schelderadarchain.processor.amqp.adapter.rabbitmq.RabbitMQReceiver;
import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPReceiverException;
import be.kdg.schelderadarchain.processor.amqp.utility.RabbitMQProperties;
import be.kdg.schelderadarchain.processor.buffering.MessageBuffer;

/**
 * Created by Olivier on 03-Nov-15.
 */
public class ProcessorController {
    private IncidentMessageController incidentMessageController;
    private PositionMessageController positionMessageController;

    private MessageBuffer messageBuffer;

    public ProcessorController() {
        RabbitMQReceiver receiver;

        this.messageBuffer = new MessageBuffer();

        try {
            // incident queue receiver
            receiver = new RabbitMQReceiver(RabbitMQProperties.getHost(), RabbitMQProperties.getReceiverIncidentQueue());
            this.incidentMessageController= new IncidentMessageController(receiver, this.messageBuffer);

            // position queue receiver
            receiver = new RabbitMQReceiver(RabbitMQProperties.getHost(), RabbitMQProperties.getReceiverPositionQueue());
            this.positionMessageController = new PositionMessageController(receiver, this.messageBuffer);
        } catch (AMQPReceiverException e) {
            System.out.println(e.getMessage());
            return; // end program
        }
    }

    public void openQueues() {
        this.incidentMessageController.startReceiving();
        this.positionMessageController.startReceiving();
    }

    public void closeQueues() {
        this.incidentMessageController.stopReceiving();
        this.positionMessageController.stopReceiving();
    }

    public static void main(String[] args) {
        ProcessorController processorController = new ProcessorController();
        processorController.openQueues();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press any key to exit.");
        scanner.nextLine();

        processorController.closeQueues();
    }
}
