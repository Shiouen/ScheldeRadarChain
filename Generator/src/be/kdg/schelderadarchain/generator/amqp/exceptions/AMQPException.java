package be.kdg.schelderadarchain.generator.amqp.exceptions;

/**
 * Created by Cas on 11/11/2015.
 */
public class AMQPException extends RuntimeException {
    public AMQPException(String message, Throwable cause){
        super(message, cause);
    }
}
