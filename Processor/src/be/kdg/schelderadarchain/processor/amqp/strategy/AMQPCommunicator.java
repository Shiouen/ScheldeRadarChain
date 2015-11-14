package be.kdg.schelderadarchain.processor.amqp.strategy;

import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;

/**
 * An AMQPCommunicator. This abstract interface class defines how the user has precise control
 * of communication using the Advanced Queueing Messaging Protocol (AMQP).
 *
 * @author Olivier Van Aken
 */
public interface AMQPCommunicator {
    /**
     * This method defines close functionality of an AMQP connection.
     *
     * @throws AMQPException
     */
    void close() throws AMQPException;
    /**
     * This method defines open functionality of an AMQP connection.
     *
     * @throws AMQPException
     */
    void open() throws AMQPException;
}
