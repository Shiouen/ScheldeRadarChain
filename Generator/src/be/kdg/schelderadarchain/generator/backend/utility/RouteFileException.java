package be.kdg.schelderadarchain.generator.backend.utility;

/**
 * Exception class for any exception concerning the route file reader.
 *
 * @author Cas Decelle
 */

public class RouteFileException extends RuntimeException {
    public RouteFileException(String message, Throwable cause){
        super(message, cause);
    }
}
