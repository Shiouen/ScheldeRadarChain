package be.kdg.schelderadarchain.processor.amqp.strategy;

import be.kdg.schelderadarchain.processor.amqp.controller.AMQPController;

/**
 * This AMPQReceiver strategy class fulfills an observable role towards an AMQPController,
 * to which it can delegate AMQP messages.
 *
 * @author Olivier Van Aken
 */
public abstract class ControlledAMQPReceiver implements AMQPReceiver {
    protected AMQPController controller;

    @Override
    public abstract void close() throws AMQPReceiverException;

    @Override
    public abstract void open() throws AMQPReceiverException;

    public final void setController(AMQPController controller){
        this.controller = controller;
    }
}
