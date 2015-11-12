package be.kdg.schelderadarchain.processor.model;

/**
 * This model class represents an Incident within the processor
 *
 * @author Olivier Van Aken.
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
