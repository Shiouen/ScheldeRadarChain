package be.kdg.schelderadarchain.processor.controller;

import java.util.Scanner;

import be.kdg.schelderadarchain.processor.amqp.adapter.RabbitMQReceiver;
import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPReceiverException;

/**
 * Created by Olivier on 03-Nov-15.
 */
public class ProcessorController {
    public static void main(String[] args) {
        MessageController messageController;

        try {
            messageController = new MessageController(new RabbitMQReceiver());
        } catch (AMQPReceiverException e) {
            System.out.println(e.getMessage());
            return; // end program
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press any key to exit.");
        scanner.nextLine();

        messageController.stopReceiving();
    }
}
