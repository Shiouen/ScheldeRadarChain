package be.kdg.schelderadarchain.processor.amqp.strategy;

/**
 * Exception class for anything concerning AMQPReceiver
 *
 * @author Olivier Van Aken
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
