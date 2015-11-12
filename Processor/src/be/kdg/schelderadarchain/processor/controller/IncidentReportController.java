package be.kdg.schelderadarchain.processor.controller;

import be.kdg.schelderadarchain.processor.amqp.adapter.rabbitmq.RabbitMQSender;
import be.kdg.schelderadarchain.processor.amqp.controller.AMQPSenderController;
import be.kdg.schelderadarchain.processor.amqp.dto.AMQPIncidentReport;
import be.kdg.schelderadarchain.processor.amqp.exception.AMQPException;

/**
 * This class is an implementation of an AMQPSenderController for AMQPIncidentReport messages.
 *
 * @author Olivier Van Aken
 */
public class IncidentReportController extends AMQPSenderController<AMQPIncidentReport> {
    public IncidentReportController(String host, String queue) {
        super(new RabbitMQSender<>(host, queue, AMQPIncidentReport.class));
    }

    @Override
    public void send(AMQPIncidentReport message) throws AMQPException {
        super.send(message);
    }
}
