package be.kdg.schelderadarchain.processor.amqp.adapter.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import be.kdg.schelderadarchain.processor.amqp.adapter.AMQPCommunicator;
import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;

/**
 * This class acts as an adapter between AMQP-based RabbitMQ functionality and
 * the AMQPCommunicator interface class, separating both codebases.
 *
 * @author Olivier Van Aken
 */
public final class RabbitMQCommunicator implements AMQPCommunicator {
    private final String host;
    private final String queue;

    private Channel channel;
    private Connection connection;

    public RabbitMQCommunicator(String host, String queue) {
        this.host = host;
        this.queue = queue;
    }

    public final Channel getChannel() { return this.channel; }
    public final Connection getConnection() { return this.connection; }
    public final String getHost() { return this.host; }
    public final String getQueue() { return this.queue; }

    @Override
    public final void close() throws AMQPException {
        try {
            this.channel.close();
        } catch (IOException e) {
            String msg = "Something went wrong trying to close the channel with RabbitMQ host=%s";
            msg = String.format(msg, this.host);
            throw new AMQPException(msg, e);
        } catch (TimeoutException e) {
            String msg = "Connection timed out trying to close the channel with RabbitMQ host=%s";
            msg =  String.format(msg, this.host);
            throw new AMQPException(msg, e);
        }

        try {
            this.connection.close();
        } catch (IOException e) {
            String msg = "Something went wrong trying to close the connection with RabbitMQ host=%s";
            msg = String.format(msg, this.host);
            throw new AMQPException(msg, e);
        }
    }

    @Override
    public final void open() throws AMQPException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(this.host);

        try {
            this.connection = factory.newConnection();
        } catch (IOException e) {
            String msg = "Something went wrong trying to setup the connection with RabbitMQ host=%s";
            msg = String.format(msg, this.host);
            throw new AMQPException(msg, e);
        } catch (TimeoutException e) {
            String msg = "Connection timed out trying to setup the connection with RabbitMQ host=%s";
            msg =  String.format(msg, this.host);
            throw new AMQPException(msg, e);
        }

        try {
            this.channel = this.connection.createChannel();
        } catch (IOException e) {
            String msg = "Something went wrong trying to setup the channel with RabbitMQ host=%s";
            msg = String.format(msg, this.host);
            throw new AMQPException(msg, e);
        }
    }
}
