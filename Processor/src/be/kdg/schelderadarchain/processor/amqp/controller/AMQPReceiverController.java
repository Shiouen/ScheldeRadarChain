package be.kdg.schelderadarchain.processor.amqp.controller;

import java.util.List;

import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPReceiverException;
import be.kdg.schelderadarchain.processor.amqp.strategy.ControlledAMQPReceiver;
import be.kdg.schelderadarchain.processor.amqp.dto.AMQPMessage;

import javax.naming.ldap.Control;

/**
 * This class acts as an abstract observer for AMQPReceiver object.
 *
 * This class provides control of its AMQPReceiver object.
 *
 * @author Olivier Van Aken
 */
public abstract class AMQPReceiverController {
    protected ControlledAMQPReceiver amqpReceiver;

    protected AMQPReceiverController(ControlledAMQPReceiver amqpReceiver) {
        this.amqpReceiver = amqpReceiver;
        this.amqpReceiver.setController(this);
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
