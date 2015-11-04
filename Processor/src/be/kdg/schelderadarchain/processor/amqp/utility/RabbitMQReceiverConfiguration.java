package be.kdg.schelderadarchain.processor.amqp.utility;

public class RabbitMQReceiverConfiguration extends AMQPConfiguration {
    private static final String HOST;
    private static final String QUEUE;

    static {
        HOST = "rabbitmq.receiver.host";
        QUEUE = "rabbitmq.receiver.queue";
    }

    public static String getHost() { return PROPERTIES.getProperty(HOST); }
    public static String getQueue() { return PROPERTIES.getProperty(QUEUE); }
}
