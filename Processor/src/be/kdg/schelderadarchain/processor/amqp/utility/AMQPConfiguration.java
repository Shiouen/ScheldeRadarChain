package be.kdg.schelderadarchain.processor.amqp.utility;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier on 04-Nov-15.
 */
public class AMQPConfiguration {
    protected final static String PROPERTY_FILE = "properties/amqp.properties";
    protected final static Properties PROPERTIES = new Properties();

    // static block is executed before main method at the time of classloading
    static {
        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE));
        } catch (IOException | IllegalArgumentException | NullPointerException ex) {
            // log error occurrence during reading of amqp properties
            System.exit(0);
        }
    }
}
