package be.kdg.schelderadarchain.processor.model;

/**
 * Created by Olivier on 09/11/2015.
 */
public class Incident extends Message {
    private String incidentType;

    public Incident(int shipId, String incidentType) {
        super(shipId);

        this.incidentType = incidentType;
    }

    public String getIncidentType() { return this.incidentType; }

    @Override
    public String toString() {
        String s = "Incident {\n\tshipId : '%s'\n\tincidentType : '%s'\n};";
        return String.format(s, this.shipId, this.incidentType);
    }
}
