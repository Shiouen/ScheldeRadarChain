package be.kdg.schelderadarchain.processor.amqp.adapter.rabbitmq;

import java.io.IOException;

import be.kdg.schelderadarchain.processor.amqp.observer.AMQPReceiverConsumer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;
import be.kdg.schelderadarchain.processor.amqp.strategy.AMQPReceiver;
import be.kdg.schelderadarchain.processor.utility.xml.XmlUtils;

/**
 * This class acts as an adapter between AMQP-based RabbitMQ functionality and
 * the AMQPCommunicator abstract interface class, separating both codebases.
 *
 * This class also acts as a strategy to receive AMQP messages based on RabbitMQs implementation.
 *
 * @author Olivier Van Aken
 */
public class RabbitMQReceiver<T> implements AMQPReceiver<T> {
    private final RabbitMQCommunicator communicator;
    private AMQPReceiverConsumer<T> consumer;

    private Class<?> type;

    public RabbitMQReceiver(String host, String queue, Class<?> type) {
        this.communicator = new RabbitMQCommunicator(host, queue);
        this.type = type;
    }

    @Override
    public void setConsumer(AMQPReceiverConsumer<T> controller) {
        this.consumer = controller;
    }

    @Override
    public void close() throws AMQPException {
        this.communicator.close();
    }

    @Override
    public void open() throws AMQPException {
        this.communicator.open();
        this.receive();
    }

    public void receive() throws AMQPException {
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

        // will buffer the asynchronously delivered calls until we're ready to use them
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
                                       byte[] body) throws IOException {
                XmlUtils<T> xmlUtils = new XmlUtils<>(RabbitMQReceiver.this.type);

                T message = xmlUtils.bind(new String(body, "UTF-8"));
                RabbitMQReceiver.this.consumer.receive(message);
                // maybe for log: System.out.println(" [x] Received '" + message + "'");
            }
        };

        try {
            channel.basicConsume(queue, true, consumer);
        } catch (IOException e) {
            String msg = "Something went wrong trying to consume a message from the queue for channel=%s " +
                    "with RabbitMQ host=%s";
            msg = String.format(msg, host, channel);
            throw new AMQPException(msg, e);
        }
        // maybe for log: System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    }
}
