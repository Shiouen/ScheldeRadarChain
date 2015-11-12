package be.kdg.schelderadarchain.processor.amqp.dto;

/**
 * This DTO class represents Cargo within the AMQP part of the processor.
 *
 * @author Olivier Van Aken
 */
public class AMQPCargo {
    private int amount;
    private String type;

    public AMQPCargo() { }

    public int getAmount() { return this.amount; }
    public String getType() { return this.type; }

    public void setAmount(int amount) { this.amount = amount; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        String s = "AMQPCargo { type : '%s', amount : '%d' }";
        return String.format(s, this.type, this.amount);
    }
}
