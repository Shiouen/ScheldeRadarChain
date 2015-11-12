package be.kdg.schelderadarchain.generator.backend.dto;

/**
 * This class is a DTO for the IncidentMessage class
 *
 * @author Cas Decelle
 */

public class IncidentMessageDTO {

    private String IMO;
    private String incidentType;

    public String getShipId() {
        return this.IMO.substring(3);
    }
    public String getIncidentType() {
        return this.incidentType;
    }

    public void setIMO(String shipId) {
        this.IMO = shipId;
    }
    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

}
