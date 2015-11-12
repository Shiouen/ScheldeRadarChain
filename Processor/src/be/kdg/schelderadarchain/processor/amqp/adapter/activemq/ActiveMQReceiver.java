package be.kdg.schelderadarchain.processor.amqp.adapter.activemq;

import be.kdg.schelderadarchain.processor.amqp.observer.AMQPReceiverConsumer;
import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;
import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPReceiver;

/**
 * This class acts as an adapter between AMQP-based ActiveMQ functionality and
 * the AMQPCommunicator abstract interface class, separating both codebases.
 *
 * This class also acts as a strategy to receive AMQP messages based on ActiveMQs implementation.
 *
 * @author Olivier Van Aken
 */
public class ActiveMQReceiver<T> implements AMQPReceiver<T> {
    private final ActiveMQCommunicator communicator;
    private AMQPReceiverConsumer<T> consumer;

    public ActiveMQReceiver(String host, String queue) {
        this.communicator = new ActiveMQCommunicator(host, queue);
    }

    @Override
    public void setConsumer(AMQPReceiverConsumer<T> controller) {
        // do something
    }

    @Override
    public void close() throws AMQPException {
        // do something
    }

    @Override
    public void open() throws AMQPException {
        // do something
    }

    public void receive() throws AMQPException {
        // do something
    }
}
