package be.kdg.schelderadarchain.generator.backend.utility;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

import java.io.*;

/**
 * Utility class that converts an object to an xml string
 * or an xml string to an object;
 *
 * @author Cas Decelle
 */

public class XmlConverter {

    private static Logger logger = Logger.getLogger(XmlConverter.class);

    public static String toXml(Object o){
        Writer writer = new StringWriter();
        try {
            Mapping mapping = new Mapping();
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("mapping/mapping.xml");
            Reader reader = new InputStreamReader(inputStream,"UTF-8");
            InputSource inputSource = new InputSource(reader);
            mapping.loadMapping(inputSource);
            Marshaller marshaller = new Marshaller();
            marshaller.setMapping(mapping);
            marshaller.setWriter(writer);
            marshaller.marshal(o);
        } catch (MarshalException | ValidationException | MappingException | IOException e) {
            String message = "Error during conversion from object to XML string";
            logger.error(message);
            throw new RouteFileException(message, e);
        }
        return writer.toString();
    }

    public static Object fromXml(String s) {
        Object o;
        BufferedReader reader = new BufferedReader(new StringReader(s));
        org.exolab.castor.xml.Unmarshaller unmarshaller = new org.exolab.castor.xml.Unmarshaller();
        try {
            o = unmarshaller.unmarshal(reader);
        } catch (MarshalException | ValidationException e) {
            String message = "Error during conversion from XML string to object";
            logger.error(message);
            throw new RouteFileException(message, e);
        }
        return o;
    }
}
