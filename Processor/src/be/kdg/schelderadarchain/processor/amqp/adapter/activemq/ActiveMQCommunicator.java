package be.kdg.schelderadarchain.processor.amqp.adapter.activemq;

import be.kdg.schelderadarchain.processor.amqp.adapter.AMQPCommunicator;
import be.kdg.schelderadarchain.processor.amqp.adapter.AMQPException;

/**
 * This class acts as an adapter between AMQP-based ActiveMQ functionality and
 * the AMQPCommunicator interface class, separating both codebases.
 *
 * @author Olivier Van Aken
 */
public final class ActiveMQCommunicator implements AMQPCommunicator {
    private final String host;
    private final String queue;

    public ActiveMQCommunicator(String host, String queue) {
        this.host = host;
        this.queue = queue;
    }

    public final String getHost() { return this.host; }
    public final String getQueue() { return this.queue; }

    @Override
    public final void close() throws AMQPException {
        // do something
    }

    @Override
    public final void open() throws AMQPException {
        // do something
    }
}
