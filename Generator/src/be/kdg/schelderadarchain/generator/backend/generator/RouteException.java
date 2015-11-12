package be.kdg.schelderadarchain.generator.backend.generator;

/**
 * Exception class for any exception concerning the route loop.
 *
 * @author Cas Decelle
 */

public class RouteException extends RuntimeException {

    public RouteException(String message, Throwable cause){
        super(message, cause);
    }
}
