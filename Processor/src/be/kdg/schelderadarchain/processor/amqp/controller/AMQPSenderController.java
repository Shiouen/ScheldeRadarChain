package be.kdg.schelderadarchain.processor.amqp.controller;

import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;
import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPSender;

/**
 * Created by Olivier on 12-Nov-15.
 */
public abstract class AMQPSenderController<T> {
    private AMQPSender<T> amqpSender;

    public AMQPSenderController(AMQPSender<T> amqpSender) {
        this.amqpSender = amqpSender;
    }

    public void send(T message) throws AMQPException {
        try {
            this.amqpSender.send(message);
        } catch (AMQPException e) {
            throw e;
        }
    }

    public final void startReceiver() throws AMQPException {
        try {
            this.amqpSender.open();
        } catch (AMQPException e) {
            throw e;
        }
    }

    public final void stopReceiver() throws AMQPException {
        try {
            this.amqpSender.close();
        } catch (AMQPException e) {
            throw e;
        }
    }
}
