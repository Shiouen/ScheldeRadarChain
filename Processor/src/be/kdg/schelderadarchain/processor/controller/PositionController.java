package be.kdg.schelderadarchain.processor.controller;

import java.util.List;
import java.util.stream.Collectors;

import be.kdg.schelderadarchain.processor.amqp.controller.AMQPReceiverController;
import be.kdg.schelderadarchain.processor.amqp.dto.AMQPPosition;
import be.kdg.schelderadarchain.processor.amqp.adapter.rabbitmq.RabbitMQReceiver;

import be.kdg.schelderadarchain.processor.eta.controller.EtaController;
import be.kdg.schelderadarchain.processor.eta.model.Eta;
import be.kdg.schelderadarchain.processor.eta.properties.EtaProperties;

import be.kdg.schelderadarchain.processor.model.mapping.ModelMapper;
import be.kdg.schelderadarchain.processor.model.Position;

import be.kdg.schelderadarchain.processor.service.MessageService;

import be.kdg.schelderadarchain.processor.buffer.MessageBuffer;

/**
 * This class is an implementation of an AMQPReceiverController for AMQPPosition messages.
 *
 * @author Olivier Van Aken
 */
public class PositionController extends AMQPReceiverController<AMQPPosition> {
    private EtaController etaController;
    private MessageService amqpMessageService;
    private MessageBuffer messageBuffer;

    public PositionController(String host, String queue, MessageBuffer messageBuffer) {
        super(new RabbitMQReceiver<>(host, queue, AMQPPosition.class));

        this.etaController = new EtaController(EtaProperties.getEtaMethod(), EtaProperties.getEtaTrigger());
        this.amqpMessageService = new MessageService();
        this.messageBuffer = messageBuffer;
    }

    @Override
    public void receive(AMQPPosition message) {
        Position position = ModelMapper.map(message);

        // add position to service layer and buffer it
        this.amqpMessageService.add(position);
        this.messageBuffer.buffer(position);

        // collect all position messages with shipId
        List<Position> positions = this.messageBuffer.getMessages(position.getShipId())
                .stream().filter(Position.class::isInstance).map(Position.class::cast)
                .collect(Collectors.toList());

        // calculate eta
        Eta eta = this.etaController.eta(positions);
        if (eta != null) {
            System.out.println(eta);
        }
    }
}
