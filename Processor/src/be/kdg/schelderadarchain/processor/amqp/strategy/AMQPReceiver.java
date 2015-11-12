package be.kdg.schelderadarchain.processor.amqp.strategy;

import be.kdg.schelderadarchain.processor.amqp.adapter.AMQPCommunicator;
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

    void setConsumer(AMQPReceiverConsumer<T> consumer);
}
