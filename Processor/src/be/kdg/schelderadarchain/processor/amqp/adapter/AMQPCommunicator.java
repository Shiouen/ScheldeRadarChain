package be.kdg.schelderadarchain.processor.amqp.adapter;

/**
 * An AMQPCommunicator. This abstract interface class defines how the user has precise control
 * of communication using the Advanced Queueing Messaging Protocol (AMQP).
 *
 * @author Olivier Van Aken
 */
public interface AMQPCommunicator {
    void close() throws AMQPException;
    void open() throws AMQPException;
}
