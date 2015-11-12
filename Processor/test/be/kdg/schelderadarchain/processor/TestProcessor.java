package be.kdg.schelderadarchain.processor;

import org.apache.log4j.Logger;

import be.kdg.schelderadarchain.processor.controller.ProcessorController;

import java.util.Scanner;

/**
 * This class functions as test entry point of a processor
 *
 * @author Olivier Van Aken
 */
public class TestProcessor {
    private final static Logger logger = Logger.getLogger(TestProcessor.class);

    public static void main(String[] args) {
        ProcessorController processorController = new ProcessorController();

        logger.info("Booting communications");
        processorController.connectCommunicators();

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press any key to exit.");
            scanner.nextLine();
        } catch(Exception e) {
            // do nothing, you just ended the program manually
        }

        logger.info("Shutting down");
        processorController.disconnectCommunicators();
    }
}
