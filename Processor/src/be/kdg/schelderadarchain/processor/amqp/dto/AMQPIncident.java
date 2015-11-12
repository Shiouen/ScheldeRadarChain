package be.kdg.schelderadarchain.processor.amqp.dto;

/**
 * This DTO class represents an Incident within the AMQP part of the processor.
 *
 * @author Olivier Van Aken
 */
public class AMQPIncident {
    private int shipId;
    private String incidentType;

    private final Class typeParameter;

    public AMQPIncident() {
        this.typeParameter = this.getClass();
    }

    public int getShipId() { return this.shipId; }
    public String getIncidentType() { return this.incidentType; }

    public void setShipId(int shipId) { this.shipId = shipId; }
    public void setIncidentType(String incidentType) { this.incidentType = incidentType; }

    @Override
    public String toString() {
        String s = "AMQPIncident {\n\tshipId : '%s'\n\tincidentType : '%s'\n};";
        return String.format(s, this.shipId, this.incidentType);
    }
}
