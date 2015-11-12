package be.kdg.schelderadarchain.processor.amqp.strategy;

import be.kdg.schelderadarchain.processor.amqp.adapter.AMQPCommunicator;
import be.kdg.schelderadarchain.processor.amqp.adapter.AMQPException;

public interface AMQPSender<T> extends AMQPCommunicator {
    @Override
    void close() throws AMQPException;

    @Override
    void open() throws AMQPException;

    void send(T message) throws AMQPException;
}
