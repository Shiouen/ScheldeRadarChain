package be.kdg.schelderadarchain.processor.amqp.adapter.rabbitmq;

import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;
import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPSender;
import be.kdg.schelderadarchain.processor.utility.xml.XmlUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * This class acts as an adapter between AMQP-based RabbitMQ functionality and
 * the AMQPCommunicator abstract interface class, separating both codebases.
 *
 * This class also acts as a strategy to send AMQP messages based on RabbitMQs implementation.
 *
 * @author Olivier Van Aken
 */
public class RabbitMQSender<T> implements AMQPSender<T> {
    private final RabbitMQCommunicator communicator;
    private final Class<?> type;

    public RabbitMQSender(String host, String queue, Class<?> type) {
        this.communicator = new RabbitMQCommunicator(host, queue);
        this.type = type;
    }

    @Override
    public void close() throws AMQPException {
        this.communicator.close();
    }

    @Override
    public void open() throws AMQPException {
        this.communicator.open();
    }

    @Override
    public void send(T message) throws AMQPException {
        Channel channel = this.communicator.getChannel();
        String host = this.communicator.getHost();
        String queue = this.communicator.getQueue();

        try {
            // make sure the queue exists
            channel.queueDeclare(queue, false, false, false, null);
        } catch (IOException e) {
            String msg = "Something went wrong trying to declare the queue for channel=%s with RabbitMQ host=%s";
            msg = String.format(msg, host, channel);
            throw new AMQPException(msg, e);
        }

        XmlUtils<T> xmlUtils = new XmlUtils<>(RabbitMQSender.this.type);
        String xml = xmlUtils.serialize(message);

        try {
            channel.basicPublish("", queue, null, xml.getBytes());
        } catch (IOException e) {
            String msg = "Something went wrong trying to send a message to the queue for channel=%s " +
                    "with RabbitMQ host=%s";
            msg = String.format(msg, host, channel);
            throw new AMQPException(msg, e);
        }
        // maybe for log: System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    }
}
