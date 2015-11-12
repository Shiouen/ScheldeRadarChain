package be.kdg.schelderadarchain.generator.generator;

import be.kdg.schelderadarchain.generator.dom.IncidentMessage;
import be.kdg.schelderadarchain.generator.dom.IncidentStatusMessage;
import be.kdg.schelderadarchain.generator.dom.PositionMessage;

/**
 * Created by Cas on 11/11/2015.
 */
public interface MessageHandler {

    void sendPositionMessage(PositionMessage message);
    void sendIncident(IncidentMessage incidentMessage);
    IncidentMessage receiveIncident(String shipId);
    IncidentStatusMessage receiveIncidentStatusMessage(String shipId);
}
