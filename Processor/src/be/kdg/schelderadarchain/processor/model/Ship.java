package be.kdg.schelderadarchain.processor.model;

import java.util.List;

/**
 * Created by Olivier on 10-Nov-15.
 */
public class Ship extends Message {
    private int passengerAmount;
    private boolean hasDangerousCargo;
    private List<Cargo> cargo;

    public Ship(int shipId, int passengerAmount, boolean hasDangerousCargo, List<Cargo> cargo) {
        super(shipId);

        this.passengerAmount = passengerAmount;
        this.hasDangerousCargo = hasDangerousCargo;
        this.cargo = cargo;
    }

    public boolean hasDangerousCargo() { return this.hasDangerousCargo; }
    public int getPassengerAmount() { return this.passengerAmount; }
    public List<Cargo> getCargo() { return this.cargo; }

    @Override
    public String toString() {
        String s = "Ship {\n\tshipId : '%s'\n\thasDangerousCargo : '%s'\n\tpassengerAmount : '%s'\n};";
        return String.format(s, this.shipId, this.hasDangerousCargo, this.passengerAmount);
    }
}
