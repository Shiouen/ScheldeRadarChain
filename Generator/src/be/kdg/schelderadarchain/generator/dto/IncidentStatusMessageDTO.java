package be.kdg.schelderadarchain.generator.dto;

/**
 * Created by Cas on 11/11/2015.
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
