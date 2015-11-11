package be.kdg.schelderadarchain.processor.buffer.properties;

import java.io.IOException;
import java.util.Properties;

public class BufferProperties {
    private static final String MESSAGE_BUFFER_DURATION;
    private static final String INFO_MESSAGE_BUFFER_DURATION;
    private static final String SHIP_SERVICE_RECEIVER_ATTEMPTS;

    protected final static String PROPERTY_FILE = "properties/buffer.properties";
    protected final static Properties PROPERTIES = new Properties();

    // static block is executed before main method at the schedule of classloading
    static {
        MESSAGE_BUFFER_DURATION = "buffer.duration.message.milliseconds";
        INFO_MESSAGE_BUFFER_DURATION = "buffer.duration.infomessage.milliseconds";
        SHIP_SERVICE_RECEIVER_ATTEMPTS = "buffer.shipservicereceiver.attempts";

        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE));
        } catch (IOException | IllegalArgumentException | NullPointerException ex) {
            // log error occurrence during reading of amqp properties
            System.exit(0);
        }
    }

    public static Integer getMessageBufferDuration() {
        return Integer.parseInt(PROPERTIES.getProperty(MESSAGE_BUFFER_DURATION));
    }
    public static Integer getInfoMessageBufferDuration() {
        return Integer.parseInt(PROPERTIES.getProperty(INFO_MESSAGE_BUFFER_DURATION));
    }
    public static Integer getShipServiceReceiverAttempts() {
        return Integer.parseInt(PROPERTIES.getProperty(SHIP_SERVICE_RECEIVER_ATTEMPTS));
    }
}
