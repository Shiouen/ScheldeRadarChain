package be.kdg.schelderadarchain.generator.amqp.adapter;

import be.kdg.schelderadarchain.generator.amqp.exceptions.AMQPException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Cas on 11/11/2015.
 */
public class RabbitMQSender implements AMQPCommunicator {
    private String host;
    private String queue;

    private final Channel channel;
    private final Connection connection;

    private String message;

    public RabbitMQSender(String host, String queue, String message) {
        this.host = host;
        this.queue = queue;
        this.message = message;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(this.host);

        try {
            this.connection = factory.newConnection();
        } catch (IOException e) {
            throw new AMQPException("Error trying to setup the connection with RabbitMQ", e);
        } catch (TimeoutException e) {
            throw new AMQPException("Timeout while trying to setup the connection with RabbitMQ", e);
        }

        try {
            this.channel = this.connection.createChannel();
        } catch (IOException e) {
            throw new AMQPException("Error trying to setup the channel with RabbitMQ", e);
        }
    }

    @Override
    public void close() throws AMQPException {
        try {
            this.channel.close();
        } catch (IOException | TimeoutException e) {
            throw new AMQPException("Error trying to close the channel with RabbitMQ", e);
        }
        try {
            this.connection.close();
        } catch (IOException e) {
            throw new AMQPException("Error trying to close the connection with RabbitMQ", e);
        }
    }

    @Override
    public void open() throws AMQPException {
        try {
            // make sure the queue exists
            this.channel.queueDeclare(this.queue, false, false, false, null);
        } catch (IOException e) {
            throw new AMQPException("Error trying to declare the queue with RabbitMQ", e);
        }
        try {
            this.channel.basicPublish("", this.queue, null, this.message.getBytes("UTF-8"));
        } catch (IOException e) {
            throw new AMQPException("Error trying to send the message", e);
        }
    }
}
