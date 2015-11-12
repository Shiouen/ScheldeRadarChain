package be.kdg.schelderadarchain.generator.backend.dom;

/**
 * Entity representing a received action report
 *
 * @author Cas Decelle
 */

public class ActionReport {

    private String type;
    private String shipId;
    private String passengerInfo;
    private String loadInfo;
    private String action;

    public ActionReport(String type, String shipId, String passengerInfo, String loadInfo, String action) {
        this.type = type;
        this.shipId = shipId;
        this.passengerInfo = passengerInfo;
        this.loadInfo = loadInfo;
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public String getShipId() {
        return shipId;
    }

    public String getPassengerInfo() {
        return passengerInfo;
    }

    public String getLoadInfo() {
        return loadInfo;
    }

    public String getAction() {
        return action;
    }
}
