package be.kdg.schelderadarchain.processor.amqp.observer;

/**
 * This interface defines how AMQP messages can be consumed.
 *
 * @author Olivier Van Aken
 */
public interface AMQPReceiverConsumer<T> {
    /**
     * Method defining how to receive messages.
     *
     * @param message The message to receive.
     */
    void receive(T message);
}
