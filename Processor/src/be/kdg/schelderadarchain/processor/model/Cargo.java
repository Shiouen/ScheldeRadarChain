package be.kdg.schelderadarchain.processor.model;

/**
 * Created by Olivier on 11-Nov-15.
 */
public class Cargo {
    private int amount;
    private String type;

    public Cargo(int amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public int getAmount() { return this.amount; }
    public String getType() { return this.type; }

    @Override
    public String toString() {
        String s = "Cargo { type : '%s', amount : '%d' }";
        return String.format(s, this.type, this.amount);
    }
}
