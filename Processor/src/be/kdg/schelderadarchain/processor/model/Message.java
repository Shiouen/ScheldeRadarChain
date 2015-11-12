package be.kdg.schelderadarchain.processor.model;

/**
 * This abstract model class enables the collection of its subclasses altogether.
 *
 * @author Olivier Van Aken
 */
public abstract class Message {
    protected int shipId;

    protected Message(int shipId) {
        this.shipId = shipId;
    }

    public int getShipId() { return this.shipId; }
    public void setShipId(int shipId) { this.shipId = shipId; }
}
