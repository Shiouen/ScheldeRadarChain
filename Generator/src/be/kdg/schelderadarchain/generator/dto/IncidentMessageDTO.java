package be.kdg.schelderadarchain.generator.dto;

/**
 * Created by Cas on 9/11/2015.
 */
public class IncidentMessageDTO {

    private String IMO;
    private String incidentType;

    public String getshipId() {
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
