package be.kdg.schelderadarchain.generator.amqp.adapter;

import be.kdg.schelderadarchain.generator.amqp.exceptions.AMQPException;

/**
 * This strategy defines a way to send or receive AMQP messages.
 *
 * @author Cas Decelle
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
