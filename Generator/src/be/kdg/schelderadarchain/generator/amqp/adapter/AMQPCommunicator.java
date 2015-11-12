package be.kdg.schelderadarchain.generator.amqp.adapter;

import be.kdg.schelderadarchain.generator.amqp.exceptions.AMQPException;

/**
 * Created by Cas on 11/11/2015.
 */
public interface AMQPCommunicator {
    void close() throws AMQPException;
    void open() throws AMQPException;
}
