package be.kdg.schelderadarchain.generator.generator;

import be.kdg.schelderadarchain.generator.dom.IncidentMessage;

/**
 * Created by Cas on 10/11/2015.
 */
public interface IncidentHandler {

    IncidentMessage receiveIncidentMessage(String shipId);

    void sendIncidentMessage(IncidentMessage incidentMessage);
}
