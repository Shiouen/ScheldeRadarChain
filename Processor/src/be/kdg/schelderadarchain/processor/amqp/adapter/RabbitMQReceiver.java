package be.kdg.schelderadarchain.processor.amqp.adapter;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import be.kdg.schelderadarchain.processor.amqp.model.AMQPMessage;
import be.kdg.schelderadarchain.processor.amqp.strategy.ControlledAMQPReceiver;
import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPReceiverException;
import be.kdg.schelderadarchain.processor.amqp.utility.RabbitMQReceiverConfiguration;

/**
 * This class acts as an adapter between AMQP-based RabbitMQ functionality and
 * the Schelderadarchain Processor's AMQPReceiver interface, separating both codebases.
 *
 * This class also acts as a strategy, through the AMQPReceiver interface abstraction, to receive AMQP messages
 * based on RabbitMQ's implementation.
 *
 * @author Olivier Van Aken
 */
public class RabbitMQReceiver extends ControlledAMQPReceiver {
    private final String host;
    private final String queue;

    private final Channel channel;
    private final Connection connection;

    public RabbitMQReceiver() throws AMQPReceiverException {
        this.host = RabbitMQReceiverConfiguration.getHost();
        this.queue = RabbitMQReceiverConfiguration.getQueue();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(this.host);

        try {
            this.connection = factory.newConnection();
        } catch (IOException e) {
            String msg = "Something went wrong trying to setup the connection with RabbitMQ host=%s";
            msg = String.format(msg, this.host);
            throw new AMQPReceiverException(msg, e);
        } catch (TimeoutException e) {
            String msg = "Connection timed out trying to setup the connection with RabbitMQ host=%s";
            msg =  String.format(msg, this.host);
            throw new AMQPReceiverException(msg, e);
        }

        try {
            this.channel = this.connection.createChannel();
        } catch (IOException e) {
            String msg = "Something went wrong trying to setup the channel with RabbitMQ host=%s";
            msg = String.format(msg, this.host);
            throw new AMQPReceiverException(msg, e);
        }
    }

    @Override
    public void close() throws AMQPReceiverException {
        try {
            this.channel.close();
        } catch (IOException e) {
            String msg = "Something went wrong trying to close the channel with RabbitMQ host=%s";
            msg = String.format(msg, this.host);
            throw new AMQPReceiverException(msg, e);
        } catch (TimeoutException e) {
            String msg = "Connection timed out trying to close the channel with RabbitMQ host=%s";
            msg =  String.format(msg, this.host);
            throw new AMQPReceiverException(msg, e);
        }

        try {
            this.connection.close();
        } catch (IOException e) {
            String msg = "Something went wrong trying to close the connection with RabbitMQ host=%s";
            msg = String.format(msg, this.host);
            throw new AMQPReceiverException(msg, e);
        }
    }

    @Override
    public void open() throws AMQPReceiverException {
        try {
            // make sure the queue exists
            channel.queueDeclare(this.queue, false, false, false, null);
        } catch (IOException e) {
            String msg = "Something went wrong trying to declare the queue for channel=%s with RabbitMQ host=%s";
            msg = String.format(msg, this.host, this.channel);
            throw new AMQPReceiverException(msg, e);
        }

        // will buffer the asynchronously delivered calls until we're ready to use them
        Consumer consumer = new DefaultConsumer(this.channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
                                       byte[] body) throws IOException {
                AMQPMessage message = new AMQPMessage(new String(body, "UTF-8"));
                RabbitMQReceiver.this.controller.receive(message);
                // maybe for log: System.out.println(" [x] Received '" + message + "'");
            }
        };

        try {
            this.channel.basicConsume(this.queue, true, consumer);
        } catch (IOException e) {
            String msg = "Something went wrong trying to poll a message from the queue for channel=%s " +
                    "with RabbitMQ host=%s";
            msg = String.format(msg, this.host, this.channel);
            throw new AMQPReceiverException(msg, e);
        }

        // maybe for log: System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    }
}
