package be.kdg.schelderadarchain.generator.dom;

import be.kdg.schelderadarchain.generator.dto.IncidentMessageDTO;

/**
 * Created by Cas on 9/11/2015.
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
