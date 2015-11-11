package be.kdg.schelderadarchain.processor.controller;

import be.kdg.schelderadarchain.processor.amqp.controller.AMQPReceiverController;
import be.kdg.schelderadarchain.processor.amqp.dto.AMQPMessage;
import be.kdg.schelderadarchain.processor.model.Incident;
import be.kdg.schelderadarchain.processor.model.mapping.ModelMapper;
import be.kdg.schelderadarchain.processor.service.MessageService;
import be.kdg.schelderadarchain.processor.amqp.strategy.ControlledAMQPReceiver;
import be.kdg.schelderadarchain.processor.buffer.MessageBuffer;

/**
 * This class is an implementation of an AMQPController for IncidentMessages.
 */
public class IncidentController extends AMQPReceiverController {
    private MessageService amqpMessageService;
    private MessageBuffer messageBuffer;

    public IncidentController(ControlledAMQPReceiver amqpReceiver, MessageBuffer messageBuffer) {
        super(amqpReceiver);

        this.amqpMessageService = new MessageService();
        this.messageBuffer = messageBuffer;
    }

    @Override
    public void receive(AMQPMessage amqpMessage) {
        Incident incident = (Incident) ModelMapper.map(amqpMessage, Incident.class);
        this.amqpMessageService.add(incident);
        this.messageBuffer.buffer(incident);
    }
}
