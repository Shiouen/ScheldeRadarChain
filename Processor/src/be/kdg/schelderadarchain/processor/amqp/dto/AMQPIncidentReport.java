package be.kdg.schelderadarchain.processor.amqp.dto;

import java.util.List;

import be.kdg.schelderadarchain.processor.model.AMQPCargo;

public class AMQPIncidentReport {
    private int shipId;
    private int passengerAmount;
    private boolean hasDangerousCargo;
    private List<AMQPCargo> cargo;
    private String action;
    private String incidentType;

    public AMQPIncidentReport() { }

    public int getShipId() { return this.shipId; }
    public int getPassengerAmount() { return this.passengerAmount; }
    public boolean isHasDangerousCargo() { return this.hasDangerousCargo; }
    public List<AMQPCargo> getCargo() { return this.cargo; }
    public String getAction() { return this.action; }
    public String getIncidentType() { return incidentType; }

    public void setShipId(int shipId) { this.shipId = shipId; }
    public void setPassengerAmount(int passengerAmount) { this.passengerAmount = passengerAmount; }
    public void setHasDangerousCargo(boolean hasDangerousCargo) { this.hasDangerousCargo = hasDangerousCargo; }
    public void setCargo(List<AMQPCargo> cargo) { this.cargo = cargo; }
    public void setAction(String action) { this.action = action; }
    public void setIncidentType(String incidentType) { this.incidentType = incidentType; }

    @Override
    public String toString() {
        String s = "IncidentReport {\n\tshipId : '%s'\n\thasDangerousCargo : '%s'\n\tpassengerAmount : '%s'" +
                "\n\tcargo : '%s'\n\taction : '%s'\n\tincidentType : '%s'" +
                "\n};";
        return String.format(s, this.shipId, this.hasDangerousCargo, this.passengerAmount, this.cargo,
                this.action, this.incidentType);
    }
}
