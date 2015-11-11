package be.kdg.schelderadarchain.processor.controller;

import be.kdg.schelderadarchain.processor.amqp.controller.AMQPReceiverController;
import be.kdg.schelderadarchain.processor.amqp.dto.AMQPMessage;
import be.kdg.schelderadarchain.processor.model.IncidentMessage;
import be.kdg.schelderadarchain.processor.model.mapping.ModelMapper;
import be.kdg.schelderadarchain.processor.service.MessageService;
import be.kdg.schelderadarchain.processor.amqp.strategy.ControlledAMQPReceiver;
import be.kdg.schelderadarchain.processor.buffer.MessageBuffer;

/**
 * This class is an implementation of an AMQPController for IncidentMessages.
 */
public class IncidentMessageController extends AMQPReceiverController {
    private MessageService amqpMessageService;
    private MessageBuffer messageBuffer;

    public IncidentMessageController(ControlledAMQPReceiver amqpReceiver, MessageBuffer messageBuffer) {
        super(amqpReceiver);

        this.amqpMessageService = new MessageService();
        this.messageBuffer = messageBuffer;
    }

    @Override
    public void receive(AMQPMessage amqpMessage) {
        IncidentMessage incidentMessage = ModelMapper.mapAmqpToIncident(amqpMessage);
        this.amqpMessageService.add(incidentMessage);
        this.messageBuffer.buffer(incidentMessage);
    }
}
