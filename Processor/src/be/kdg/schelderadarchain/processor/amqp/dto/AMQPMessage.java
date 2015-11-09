package be.kdg.schelderadarchain.processor.amqp.dto;

/**
 * This model class provides a simple way to pass on an AMQPMessage string.
 *
 * @author Olivier Van Aken
 */
public class AMQPMessage {
    private String message;

    public AMQPMessage(String message) { this.message = message; }

    public String getMessage() { return this.message; }

    @Override
    public String toString() {
        return "AMQPMessage {\n" + this.message + "\n};";
    }
}
