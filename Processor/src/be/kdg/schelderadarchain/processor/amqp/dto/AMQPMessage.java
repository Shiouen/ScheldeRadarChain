package be.kdg.schelderadarchain.processor.amqp.dto;

/**
 * Created by Olivier on 04-Nov-15.
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
