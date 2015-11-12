package be.kdg.schelderadarchain.processor.utility.xml;

import java.io.*;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Created by Olivier on 11-Nov-15.
 */
public final class XmlUtils<T> {
    private final Class<?> type;

    public XmlUtils(Class<?> type) {
        this.type = type;
    }

    public T bind(String xml) {
        Reader reader = new StringReader(xml);

        try {
            return (T) Unmarshaller.unmarshal(this.type, reader);
        } catch (MarshalException e) {
            // lol
            return null;
        } catch (ValidationException e) {
            // lol
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
            // lol
            return null;
        } catch (ValidationException e) {
            // lol
            return null;
        } catch (IOException e) {
            // lol
            return null;
        }
    }
}
