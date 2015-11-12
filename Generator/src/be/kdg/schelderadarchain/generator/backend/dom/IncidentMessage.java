package be.kdg.schelderadarchain.generator.backend.dom;

import be.kdg.schelderadarchain.generator.backend.dto.IncidentMessageDTO;

/**
 * Entity representing a received incident message
 *
 * @author Cas Decelle
 */

public class IncidentMessage {

    private String shipId;
    private String incidentType;

    public IncidentMessage(IncidentMessageDTO incidentMessageDTO) {
        this.shipId = incidentMessageDTO.getShipId();
        this.incidentType = incidentMessageDTO.getIncidentType();
    }

    public String getshipId() {
        return this.shipId;
    }
    public String getIncidentType() {
        return this.incidentType;
    }
}
