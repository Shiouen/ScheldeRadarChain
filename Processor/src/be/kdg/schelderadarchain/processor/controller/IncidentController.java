package be.kdg.schelderadarchain.processor.controller;

import be.kdg.schelderadarchain.processor.amqp.controller.AMQPReceiverController;
import be.kdg.schelderadarchain.processor.amqp.dto.AMQPIncident;
import be.kdg.schelderadarchain.processor.amqp.adapter.rabbitmq.RabbitMQReceiver;
import be.kdg.schelderadarchain.processor.amqp.dto.AMQPIncidentReport;
import be.kdg.schelderadarchain.processor.amqp.properties.RabbitMQProperties;
import be.kdg.schelderadarchain.processor.model.Incident;
import be.kdg.schelderadarchain.processor.model.IncidentReport;
import be.kdg.schelderadarchain.processor.model.Ship;
import be.kdg.schelderadarchain.processor.model.mapping.ModelMapper;
import be.kdg.schelderadarchain.processor.service.MessageService;
import be.kdg.schelderadarchain.processor.buffer.MessageBuffer;

/**
 * This class is an implementation of an AMQPReceiverController for AMQPIncident messages.
 *
 * @author Olivier Van Aken
 */
public class IncidentController extends AMQPReceiverController<AMQPIncident> {
    private IncidentReportController incidentReportController;
    private MessageService amqpMessageService;
    private MessageBuffer messageBuffer;

    public IncidentController(String host, String queue, MessageBuffer messageBuffer) {
        super(new RabbitMQReceiver<>(host, queue, AMQPIncident.class));

        String sendHost = RabbitMQProperties.getHost();
        String sendQueue = RabbitMQProperties.getReceiverIncidentQueue();
        this.incidentReportController = new IncidentReportController(sendHost, sendQueue);

        this.amqpMessageService = new MessageService();
        this.messageBuffer = messageBuffer;
    }

    @Override
    public void receive(AMQPIncident message) {
        Incident incident = ModelMapper.map(message);
        this.amqpMessageService.add(incident);
        this.messageBuffer.buffer(incident);

        // Incident reporting is not implemented
        // this.incidentReportController.send();
    }
}
