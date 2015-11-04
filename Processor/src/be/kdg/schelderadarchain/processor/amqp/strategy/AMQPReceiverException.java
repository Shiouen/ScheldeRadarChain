package be.kdg.schelderadarchain.processor.amqp.strategy;

/**
 * Created by Olivier on 03-Nov-15.
 */
public class AMQPReceiverException extends Exception {
    public AMQPReceiverException(String message) {
        super(message);
    }

    public AMQPReceiverException(String message, Throwable cause) {
        super(message, cause);
    }

    public AMQPReceiverException(Throwable cause) {
        super(cause);
    }
}
