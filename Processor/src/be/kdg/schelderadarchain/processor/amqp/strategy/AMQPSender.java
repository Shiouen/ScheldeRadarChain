package be.kdg.schelderadarchain.processor.amqp.strategy;

import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;

/**
 * This strategy interface defines a way to send AMQP messages.
 *
 * @author Olivier Van Aken
 */
public interface AMQPSender<T> extends AMQPCommunicator {
    @Override
    void close() throws AMQPException;

    @Override
    void open() throws AMQPException;

    /**
     * This method defines sending functionality of an AMQP message.
     *
     * @param message The message to send.
     * @throws AMQPException
     */
    void send(T message) throws AMQPException;
}
