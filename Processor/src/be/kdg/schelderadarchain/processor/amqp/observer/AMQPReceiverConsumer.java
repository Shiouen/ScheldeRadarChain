package be.kdg.schelderadarchain.processor.amqp.observer;

/**
 *
 * @param <T>
 */
public interface AMQPReceiverConsumer<T> {
    void receive(T message);
}
