package be.kdg.schelderadarchain.processor.controller;

import be.kdg.schelderadarchain.processor.amqp.controller.AMQPReceiverController;
import be.kdg.schelderadarchain.processor.amqp.dto.AMQPMessage;
import be.kdg.schelderadarchain.processor.eta.controller.EtaController;
import be.kdg.schelderadarchain.processor.eta.properties.EtaProperties;
import be.kdg.schelderadarchain.processor.model.mapping.ModelMapper;
import be.kdg.schelderadarchain.processor.model.Position;
import be.kdg.schelderadarchain.processor.service.MessageService;
import be.kdg.schelderadarchain.processor.amqp.strategy.ControlledAMQPReceiver;
import be.kdg.schelderadarchain.processor.buffer.MessageBuffer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is an implementation of an AMQPController for PositionMessages.
 */
public class PositionController extends AMQPReceiverController {
    private EtaController etaController;
    private MessageService amqpMessageService;
    private MessageBuffer messageBuffer;

    public PositionController(ControlledAMQPReceiver amqpReceiver, MessageBuffer messageBuffer) {
        super(amqpReceiver);

        this.etaController = new EtaController(EtaProperties.getEtaMethod(), EtaProperties.getEtaTrigger());
        this.amqpMessageService = new MessageService();
        this.messageBuffer = messageBuffer;
    }

    @Override
    public void receive(AMQPMessage amqpMessage) {
        Position position = (Position) ModelMapper.map(amqpMessage, Position.class);
        this.amqpMessageService.add(position);
        this.messageBuffer.buffer(position);

        // collect all position messages with shipId
        List<Position> positions = this.messageBuffer.getMessages(position.getShipId())
                .stream().filter(Position.class::isInstance).map(Position.class::cast)
                .collect(Collectors.toList());

        Timestamp timestamp = this.etaController.eta(positions);
        if (timestamp != null) {
            System.out.println(timestamp.getTime());
        }
    }
}
