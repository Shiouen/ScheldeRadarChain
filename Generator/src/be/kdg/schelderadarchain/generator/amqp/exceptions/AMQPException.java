package be.kdg.schelderadarchain.generator.amqp.exceptions;

/**
 * Exception class for any exception concerning the AMQP.
 *
 * @author Cas Decelle
 */

public class AMQPException extends RuntimeException {
    public AMQPException(String message, Throwable cause){
        super(message, cause);
    }
}
