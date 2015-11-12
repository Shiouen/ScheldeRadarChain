package be.kdg.schelderadarchain.processor.amqp.controller;

import be.kdg.schelderadarchain.processor.amqp.observer.AMQPReceiverConsumer;
import be.kdg.schelderadarchain.processor.amqp.adapter.AMQPException;
import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPReceiver;

/**
 * Created by Olivier on 12-Nov-15.
 */
public abstract class AMQPReceiverController<T> implements AMQPReceiverConsumer<T> {
    private AMQPReceiver<T> amqpReceiver;

    public AMQPReceiverController(AMQPReceiver<T> amqpReceiver) {
        this.amqpReceiver = amqpReceiver;
        this.amqpReceiver.setConsumer(this);
    }

    @Override
    public abstract void receive(T message);

    public final void startReceiver() throws AMQPException {
        try {
            this.amqpReceiver.open();
        } catch (AMQPException e) {
            throw e;
        }
    }

    public final void stopReceiver() throws AMQPException {
        try {
            this.amqpReceiver.close();
        } catch (AMQPException e) {
            throw e;
        }
    }
}
