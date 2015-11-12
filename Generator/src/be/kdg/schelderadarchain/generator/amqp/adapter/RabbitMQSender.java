package be.kdg.schelderadarchain.generator.amqp.adapter;

import be.kdg.schelderadarchain.generator.amqp.exceptions.AMQPException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * This class acts as an adapter between AMQP-based RabbitMQ functionality and
 * the AMQPCommunicator abstract interface class, separating both codebases.
 *
 * This class also acts as a strategy to send AMQP messages based on RabbitMQs implementation.
 *
 * @author Cas Decelle
 */

public class RabbitMQSender implements AMQPCommunicator {
    private String host;
    private String queue;

    private final Channel channel;
    private final Connection connection;
    private final Logger logger = Logger.getLogger(this.getClass());

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
            String msg = "Error trying to setup the connection with RabbitMQ";
            this.logger.error(msg);
            throw new AMQPException(msg, e);
        } catch (TimeoutException e) {
            String msg = "Timeout while trying to setup the connection with RabbitMQ";
            this.logger.error(msg);
            throw new AMQPException(msg, e);
        }

        try {
            this.channel = this.connection.createChannel();
        } catch (IOException e) {
            String msg = "Error trying to setup the channel with RabbitMQ";
            this.logger.error(msg);
            throw new AMQPException(msg, e);
        }
    }

    @Override
    public void close() throws AMQPException {
        try {
            this.channel.close();
        } catch (IOException e) {
            String message = "Error trying to close the channel with RabbitMQ";
            this.logger.error(message);
            throw new AMQPException(message, e);
        } catch (TimeoutException e) {
            String message = "Timeout trying to close the channel with RabbitMQ";
            this.logger.error(message);
            throw new AMQPException(message, e);
        }
        try {
            this.connection.close();
        } catch (IOException e) {
            String message = "Error trying to close the connection with RabbitMQ";
            this.logger.error(message);
            throw new AMQPException(message, e);
        }
    }

    @Override
    public void open() throws AMQPException {
        try {
            // make sure the queue exists
            this.channel.queueDeclare(this.queue, false, false, false, null);
        } catch (IOException e) {
            String message = "Error trying to declare the queue with RabbitMQ";
            this.logger.error(message);
            throw new AMQPException(message, e);
        }
        try {
            this.channel.basicPublish("", this.queue, null, this.message.getBytes("UTF-8"));
        } catch (IOException e) {
            String message = "Error trying to send the message";
            this.logger.error(message);
            throw new AMQPException(message, e);
        }
    }
}
