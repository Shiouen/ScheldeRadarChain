package be.kdg.schelderadarchain.processor.buffer.dto;

/**
 * This DTO class represents Cargo within the buffer part of the processor.
 *
 * @author Olivier Van Aken
 */
public class ShipServiceCargo {
    private int amount;
    private String type;

    public ShipServiceCargo() { }

    public int getAmount() { return this.amount; }
    public String getType() { return this.type; }

    public void setAmount(int amount) { this.amount = amount; }
    public void setType(String type) { this.type = type; }
}
