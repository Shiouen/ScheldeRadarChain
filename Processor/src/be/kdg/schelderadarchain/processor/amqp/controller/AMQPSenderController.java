package be.kdg.schelderadarchain.processor.amqp.controller;

import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;
import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPSender;

/**
 * This abstract class enables its subclasses control over an AMQPSender<T> object
 *
 * @author Olivier Van Aken
 */
public abstract class AMQPSenderController<T> {
    private AMQPSender<T> amqpSender;

    public AMQPSenderController(AMQPSender<T> amqpSender) {
        this.amqpSender = amqpSender;
    }

    /**
     * Method defining a default implementation on how to send messages.
     *
     * @param message The message to receive.
     */
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
