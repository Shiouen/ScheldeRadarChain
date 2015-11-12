package be.kdg.schelderadarchain.generator.backend.utility;

/**
 * Exception class for any exception concerning conversion to and from an object;
 *
 * @author Cas Decelle
 */

public class ConversionException extends RuntimeException {
    public ConversionException(String message, Throwable cause){
        super(message, cause);
    }
}
