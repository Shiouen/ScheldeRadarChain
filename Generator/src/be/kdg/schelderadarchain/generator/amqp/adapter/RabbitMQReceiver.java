package be.kdg.schelderadarchain.generator.amqp.adapter;

import be.kdg.schelderadarchain.generator.amqp.exceptions.AMQPException;
import be.kdg.schelderadarchain.generator.dom.ActionReport;
import be.kdg.schelderadarchain.generator.generator.ActionReportListener;
import be.kdg.schelderadarchain.generator.utility.XmlConverter;
import com.rabbitmq.client.*;
import org.exolab.castor.dsml.XML;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Cas on 11/11/2015.
 */
public class RabbitMQReceiver implements AMQPCommunicator {
    private String host;
    private String queue;

    private final Channel channel;
    private final Connection connection;

    private ActionReportListener actionReportListener;

    public RabbitMQReceiver(String host, String queue, ActionReportListener actionReportListener) {
        this.host = host;
        this.queue = queue;
        this.actionReportListener = actionReportListener;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(this.host);

        try {
            this.connection = factory.newConnection();
        } catch (IOException e) {
            throw new AMQPException("Error trying to setup the connection with RabbitMQ", e);
        } catch (TimeoutException e) {
            throw new AMQPException("Error trying to setup the connection with RabbitMQ", e);
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

        // will buffer the asynchronously delivered calls until we're ready to use them
        Consumer consumer = new DefaultConsumer(this.channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                ActionReport actionReport = (ActionReport)XmlConverter.fromXml(new String(body, "UTF-8"));
                actionReportListener.onActionReportReceived(actionReport);
            }
        };

        try {
            this.channel.basicConsume(this.queue, true, consumer);
        } catch (IOException e) {
            throw new AMQPException("Error trying to poll a message from the queue", e);
        }

    }
}
