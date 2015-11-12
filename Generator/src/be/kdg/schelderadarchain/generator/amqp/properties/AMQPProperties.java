package be.kdg.schelderadarchain.generator.amqp.properties;

import be.kdg.schelderadarchain.generator.amqp.exceptions.AMQPException;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Cas on 11/11/2015.
 */
public class AMQPProperties {
    protected final static String PROPERTY_FILE = "properties/amqp.properties";
    protected final static Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE));
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            throw new AMQPException("Error when loading the AMQP properties file", e);
        }
    }
}
