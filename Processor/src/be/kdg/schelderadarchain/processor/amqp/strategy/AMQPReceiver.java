package be.kdg.schelderadarchain.processor.amqp.strategy;

import be.kdg.schelderadarchain.processor.amqp.adapter.AMQPCommunicator;
import be.kdg.schelderadarchain.processor.amqp.adapter.AMQPException;
import be.kdg.schelderadarchain.processor.amqp.observer.AMQPReceiverConsumer;

/**
 * Created by Olivier on 11-Nov-15.
 */
public interface AMQPReceiver<T> extends AMQPCommunicator {
    @Override
    void close() throws AMQPException;

    @Override
    void open() throws AMQPException;

    void setConsumer(AMQPReceiverConsumer<T> consumer);
}
