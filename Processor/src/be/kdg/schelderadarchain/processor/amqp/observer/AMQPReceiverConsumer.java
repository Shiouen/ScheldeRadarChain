package be.kdg.schelderadarchain.processor.amqp.observer;

public interface AMQPReceiverConsumer<T> {
    void receive(T message);
}
