package be.kdg.schelderadarchain.processor.amqp.strategy;

import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;
import be.kdg.schelderadarchain.processor.amqp.observer.AMQPReceiverConsumer;

/**
 * This strategy interface defines a way to receive AMQP messages.
 *
 * @author Olivier Van Aken
 */
public interface AMQPReceiver<T> extends AMQPCommunicator {
    @Override
    void close() throws AMQPException;

    @Override
    void open() throws AMQPException;

    /**
     * This method defines that all AMQPReceivers should be able
     * to have a consumer.
     *
     * @param consumer Consumer to pass messages to.
     */
    void setConsumer(AMQPReceiverConsumer<T> consumer);
}
