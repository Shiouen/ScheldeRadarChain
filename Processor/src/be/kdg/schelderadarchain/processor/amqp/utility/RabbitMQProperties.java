package be.kdg.schelderadarchain.processor.amqp.utility;

public class RabbitMQProperties extends AMQPProperties {
    private static final String HOST;
    private static final String RECEIVER_INCIDENT_QUEUE;
    private static final String RECEIVER_POSITION_QUEUE;
    private static final String SENDER_INCIDENT_ACTION_REPORT_QUEUE;

    static {
        HOST = "rabbitmq.receiver.host";
        RECEIVER_INCIDENT_QUEUE = "rabbitmq.receiver.incident.queue";
        RECEIVER_POSITION_QUEUE = "rabbitmq.receiver.position.queue";
        SENDER_INCIDENT_ACTION_REPORT_QUEUE = "rabbitmq.sender.report.queue";
    }

    public static String getHost() { return PROPERTIES.getProperty(HOST); }
    public static String getReceiverIncidentQueue() { return PROPERTIES.getProperty(RECEIVER_INCIDENT_QUEUE); }
    public static String getReceiverPositionQueue() { return PROPERTIES.getProperty(RECEIVER_POSITION_QUEUE); }
    public static String getSenderReportQueue() { return PROPERTIES.getProperty(SENDER_INCIDENT_ACTION_REPORT_QUEUE); }
}
