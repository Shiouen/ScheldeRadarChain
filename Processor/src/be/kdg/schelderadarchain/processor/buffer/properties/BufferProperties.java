package be.kdg.schelderadarchain.processor.buffer.properties;

import java.io.IOException;
import java.util.Properties;

public class BufferProperties {
    private static final String BUFFER_DURATION;

    protected final static String PROPERTY_FILE = "properties/buffer.properties";
    protected final static Properties PROPERTIES = new Properties();

    // static block is executed before main method at the schedule of classloading
    static {
        BUFFER_DURATION = "buffer.duration.milliseconds";

        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE));
        } catch (IOException | IllegalArgumentException | NullPointerException ex) {
            // log error occurrence during reading of amqp properties
            System.exit(0);
        }
    }

    public static Integer getBufferDuration() { return Integer.parseInt(PROPERTIES.getProperty(BUFFER_DURATION)); }
}
