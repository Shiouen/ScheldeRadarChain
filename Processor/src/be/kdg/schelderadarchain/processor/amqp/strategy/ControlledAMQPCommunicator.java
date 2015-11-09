package be.kdg.schelderadarchain.processor.amqp.strategy;

import be.kdg.schelderadarchain.processor.amqp.controller.AMQPReceiverController;

/**
 * Created by Olivier on 09/11/2015.
 */
public abstract class ControlledAMQPCommunicator {
    protected AMQPReceiverController controller;

    public final void setController(AMQPReceiverController controller){
        this.controller = controller;
    }
}
