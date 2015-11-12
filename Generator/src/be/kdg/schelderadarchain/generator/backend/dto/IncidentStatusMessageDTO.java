package be.kdg.schelderadarchain.generator.backend.dto;

/**
 * This class is a DTO for the IncidentStatusMessage class
 *
 * @author Cas Decelle
 */

public class IncidentStatusMessageDTO {

    private String IMO;
    private String status;

    public String getShipId(){
        return this.IMO;
    }
    public boolean getStatus(){
        return status.equalsIgnoreCase("true") ? true : false;
    }

    public void setIMO(String IMO) {
        this.IMO = IMO;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
