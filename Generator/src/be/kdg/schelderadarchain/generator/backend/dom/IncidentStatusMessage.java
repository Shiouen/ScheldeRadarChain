package be.kdg.schelderadarchain.generator.backend.dom;

import be.kdg.schelderadarchain.generator.backend.dto.IncidentStatusMessageDTO;

/**
 * Entity representing a received incident status message
 *
 * @author Cas Decelle
 */

public class IncidentStatusMessage {

    private String shipId;
    private boolean status;

    public IncidentStatusMessage(IncidentStatusMessageDTO incidentStatusMessageDTO) {
        this.shipId = incidentStatusMessageDTO.getShipId();
        this.status = incidentStatusMessageDTO.getStatus();
    }

    public String getshipId() {
        return this.shipId;
    }
    public boolean getStatus() {
        return this.status;
    }
}
