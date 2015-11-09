package be.kdg.schelderadarchain.processor.model;

public abstract class Message {
    protected int shipId;

    public int getShipId() { return this.shipId; }
    public void setShipId(int shipId) { this.shipId = shipId; }
}
