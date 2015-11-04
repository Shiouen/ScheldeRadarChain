package be.kdg.schelderadarchain.processor.amqp.strategy;

/**
 * An AMQPReceiver. This interface defines how the user has precise control over when to receive AMQP messages.
 *
 * @author Olivier Van Aken
 */
public interface AMQPReceiver {
    void close() throws AMQPReceiverException;
    void open() throws AMQPReceiverException;
}
