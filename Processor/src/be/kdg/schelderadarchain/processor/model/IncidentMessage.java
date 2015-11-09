package be.kdg.schelderadarchain.processor.model;

/**
 * Created by Olivier on 09/11/2015.
 */
public class IncidentMessage extends Message {
    private String incidentType;

    public IncidentMessage() { }

    public String getIncidentType() { return incidentType; }
    public void setIncidentType(String incidentType) { this.incidentType = incidentType; }

    @Override
    public String toString() {
        String s = "IncidentMessage {\n\tshipId : '%s'\n\tincidentType : '%s'\n};";
        return String.format(s, this.shipId, this.incidentType);
    }
}
