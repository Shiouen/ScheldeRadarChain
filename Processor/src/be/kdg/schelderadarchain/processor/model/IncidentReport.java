package be.kdg.schelderadarchain.processor.model;

import java.util.List;

/**
 * This model class represents an IncidentReport within the processor.
 *
 * @author Olivier Van Aken
 */
public class IncidentReport extends Message {
    private int passengerAmount;
    private boolean hasDangerousCargo;
    private List<Cargo> cargo;
    private String action;
    private String incidentType;

    public IncidentReport(Incident incident, Ship ship, String action) {
        super(ship.getShipId());

        this.passengerAmount = ship.getPassengerAmount();
        this.hasDangerousCargo = ship.hasDangerousCargo();
        this.cargo = ship.getCargo();
        this.action = action;
        this.incidentType = incident.getIncidentType();
    }

    public IncidentReport(int shipId, int passengerAmount, boolean hasDangerousCargo, List<Cargo> cargo,
                          String action, String incidentType) {
        super(shipId);

        this.passengerAmount = passengerAmount;
        this.hasDangerousCargo = hasDangerousCargo;
        this.cargo = cargo;
        this.action = action;
        this.incidentType = incidentType;
    }

    @Override
    public String toString() {
        String s = "IncidentReport {\n\tshipId : '%s'\n\thasDangerousCargo : '%s'\n\tpassengerAmount : '%s'" +
                "\n\tcargo : '%s'\n\taction : '%s'\n\tincidentType : '%s'" +
                "\n};";
        return String.format(s, this.shipId, this.hasDangerousCargo, this.passengerAmount, this.cargo,
                this.action, this.incidentType);
    }
}
