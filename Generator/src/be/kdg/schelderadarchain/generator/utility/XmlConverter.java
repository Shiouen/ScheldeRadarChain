package be.kdg.schelderadarchain.generator.utility;

import org.xml.sax.InputSource;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

import java.io.*;
import java.net.URL;

/**
 * Created by Cas on 9/11/2015.
 */
public class XmlConverter {

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
            throw new UtilityException("Error during conversion to XML string", e);
        }
        return writer.toString();
    }
}
