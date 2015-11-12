package be.kdg.schelderadarchain.generator.dom;

import be.kdg.schelderadarchain.generator.dto.IncidentMessageDTO;
import be.kdg.schelderadarchain.generator.dto.IncidentStatusMessageDTO;

/**
 * Created by Cas on 11/11/2015.
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
