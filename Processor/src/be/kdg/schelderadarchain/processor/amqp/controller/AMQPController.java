package be.kdg.schelderadarchain.processor.amqp.controller;

import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPReceiverException;
import be.kdg.schelderadarchain.processor.amqp.strategy.ControlledAMQPReceiver;
import be.kdg.schelderadarchain.processor.amqp.model.AMQPMessage;

/**
 * This class acts as an abstract observer for AMQPReceiver objects.
 *
 * This class provides control of its AMQPReceiver object.
 *
 * @author Olivier Van Aken
 */
public abstract class AMQPController {
    protected ControlledAMQPReceiver amqpReceiver;

    protected AMQPController(ControlledAMQPReceiver amqpReceiver) {
        this.amqpReceiver = amqpReceiver;
        this.amqpReceiver.setController(this);

        this.startReceiving();
    }

    public abstract void receive(AMQPMessage AmqpMessage);

    public final void startReceiving() {
        try {
            this.amqpReceiver.open();
        } catch (AMQPReceiverException e) {
            // log as error, exit
        }
    }

    public final void stopReceiving() {
        try {
            this.amqpReceiver.close();
        } catch (AMQPReceiverException e) {
            // log as error, exit
        }
    }
}
