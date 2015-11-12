package be.kdg.schelderadarchain.processor.amqp.adapter;

/**
 * Exception class for any exception concerning the AMQP.
 *
 * @author Olivier Van Aken
 */
public class AMQPException extends Exception {
    public AMQPException(String message) {
        super(message);
    }

    public AMQPException(String message, Throwable cause) {
        super(message, cause);
    }

    public AMQPException(Throwable cause) {
        super(cause);
    }
}
