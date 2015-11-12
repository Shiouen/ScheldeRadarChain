package be.kdg.schelderadarchain.processor.amqp.properties;

import java.io.IOException;
import java.util.Properties;

/**
 * Properties handler class for AMQP properties.
 *
 * @author Olivier Van Aken
 */
public class AMQPProperties {
    protected final static String PROPERTY_FILE = "properties/amqp.properties";
    protected final static Properties PROPERTIES = new Properties();

    // static block is executed before main method at the schedule of classloading
    static {
        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE));
        } catch (IOException | IllegalArgumentException | NullPointerException ex) {
            // log error occurrence during reading of amqp properties
            System.exit(0);
        }
    }
}
