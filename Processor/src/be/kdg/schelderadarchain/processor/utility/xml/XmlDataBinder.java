package be.kdg.schelderadarchain.processor.utility.xml;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Created by Olivier on 11-Nov-15.
 */
public final class XmlDataBinder<T> {
    private final Class<?> type;

    public XmlDataBinder(Class<?> type) {
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
}
