package be.kdg.schelderadarchain.generator.amqp.properties;

/**
 * This class contains the properties for a connection with RabbitMQ.
 *
 * @author Cas Decelle
 */

public class RabbitMQProperties extends AMQPProperties {
    private static final String HOST;
    private static final String SENDER_INCIDENT_QUEUE;
    private static final String SENDER_POSITION_MESSAGE_QUEUE;
    private static final String RECEIVER_INCIDENT_ACTION_REPORT_QUEUE;

    static {
        HOST = "rabbitmq.receiver.host";
        SENDER_INCIDENT_QUEUE = "rabbitmq.sender.incident.queue";
        SENDER_POSITION_MESSAGE_QUEUE = "rabbitmq.sender.position.queue";
        RECEIVER_INCIDENT_ACTION_REPORT_QUEUE = "rabbitmq.receiver.report.queue";
    }

    public static String getHost() { return PROPERTIES.getProperty(HOST); }
    public static String getSenderIncidentQueue() { return PROPERTIES.getProperty(SENDER_INCIDENT_QUEUE); }
    public static String getSenderPositionMessageQueue() { return PROPERTIES.getProperty(SENDER_POSITION_MESSAGE_QUEUE); }
    public static String getReceiverIncidentActionReportQueue() { return PROPERTIES.getProperty(RECEIVER_INCIDENT_ACTION_REPORT_QUEUE); }
}

