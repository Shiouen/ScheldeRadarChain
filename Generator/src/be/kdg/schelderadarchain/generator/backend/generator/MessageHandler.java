package be.kdg.schelderadarchain.generator.backend.generator;

import be.kdg.schelderadarchain.generator.backend.dom.IncidentMessage;
import be.kdg.schelderadarchain.generator.backend.dom.IncidentStatusMessage;
import be.kdg.schelderadarchain.generator.backend.dom.PositionMessage;

/**
 * This interface provides the functionality for sending and receiving messages.
 *
 * @author Cas Decelle
 */

public interface MessageHandler {
    /*
    * Contains the implementation to send a position message over a message broker
    * @param message
    */
    void sendPositionMessage(PositionMessage message);
    /*
    * Contains the implementation to send an incident message over a message broker
    * @param message
    */
    void sendIncident(IncidentMessage message);
    /*
    * Contains the implementation to receive an incident from an incident service
    * @param shipId
    * @return the received incident
    */
    IncidentMessage receiveIncident(String shipId);
    /*
    * Contains the implementation to receive an incident status from an incident service
    * @param shipId
    * @return the received incident status
    */
    IncidentStatusMessage receiveIncidentStatus(String shipId);
}
