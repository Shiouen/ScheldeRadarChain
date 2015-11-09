package be.kdg.schelderadarchain.processor.amqp.service;

import be.kdg.schelderadarchain.processor.amqp.model.AMQPMessage;

/**
 * This class acts a service for AMQPMessage objects.
 *
 * @author Olivier Van Aken
 */
public class AMQPMessageService {
    public AMQPMessageService() { }

    public AMQPMessage add(AMQPMessage amqpMessage) {
        System.out.println(amqpMessage);
        return amqpMessage;
    }
}
