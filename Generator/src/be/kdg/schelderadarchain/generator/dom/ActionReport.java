package be.kdg.schelderadarchain.generator.dom;

import be.kdg.schelderadarchain.generator.generator.ActionReportListener;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Cas on 11/11/2015.
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
