package be.kdg.schelderadarchain.processor.utility.xml;

import java.io.*;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Generic Xml utility class for databinding and serialization.
 *
 * @param <T> Generic Type
 * @author Olivier Van Aken
 */
public final class XmlUtils<T> {
    private final Class<?> type;

    private final static Logger logger = Logger.getLogger(XmlUtils.class);

    public XmlUtils(Class<?> type) {
        this.type = type;
    }

    public T bind(String xml) {
        Reader reader = new StringReader(xml);

        try {
            return (T) Unmarshaller.unmarshal(this.type, reader);
        } catch (MarshalException e) {
            logger.error("Something went wrong binding XML to an Object");
            return null;
        } catch (ValidationException e) {
            logger.error("Something went wrong binding XML to an Object");
            return null;
        }
    }

    public String serialize(T pojo) {
        Writer writer =	new StringWriter();

        try {
            Marshaller.marshal(pojo, writer);
            String xml = writer.toString();

            writer.flush();
            writer.close();

            return xml;
        } catch (MarshalException e) {
            logger.error("Something went wrong serializing an object to XML");
            return null;
        } catch (ValidationException e) {
            logger.error("Something went wrong serializing an object to XML");
            return null;
        } catch (IOException e) {
            logger.error("Something went wrong serializing an object to XML");
            return null;
        }
    }
}
