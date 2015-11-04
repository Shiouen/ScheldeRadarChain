package be.kdg.schelderadarchain.processor.controller;

import be.kdg.schelderadarchain.processor.amqp.controller.AMQPController;
import be.kdg.schelderadarchain.processor.amqp.model.AMQPMessage;
import be.kdg.schelderadarchain.processor.amqp.service.AMQPMessageService;
import be.kdg.schelderadarchain.processor.amqp.strategy.ControlledAMQPReceiver;
import be.kdg.schelderadarchain.processor.buffering.MessageBuffer;
import be.kdg.schelderadarchain.processor.model.Message;

/**
 * This class is an implementation of an AMQPController.
 */
public class MessageController extends AMQPController {
    private AMQPMessageService amqpMessageService;
    private MessageBuffer messageBuffer;

    public MessageController(ControlledAMQPReceiver amqpReceiver) {
        super(amqpReceiver);

        this.amqpMessageService = new AMQPMessageService();
        this.messageBuffer = new MessageBuffer();
    }

    @Override
    public void receive(AMQPMessage amqpMessage) {
        this.amqpMessageService.add(amqpMessage);
        this.messageBuffer.buffer(Message.unmarshal(amqpMessage));
    }
}
