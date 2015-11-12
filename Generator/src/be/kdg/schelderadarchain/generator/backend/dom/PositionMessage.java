package be.kdg.schelderadarchain.generator.backend.dom;

import java.util.Date;
import java.util.Random;

/**
 * Entity representing a position message
 *
 * @author Cas Decelle
 */

public class PositionMessage {

    private String shipId;
    private String delay;
    private String stationId;
    private Date timestamp;
    private String distanceToLoadingDock;

    private final int LIST_SIZE = 10;
    private final String[] shipIds = new String[LIST_SIZE];
    private final String[] centraleIds = new String[] {"Amsterdam","Delfzijl","Eemshaven","Hengelo","Meppel","Moerdijk","Rotterdam","Terneuzen","Veghel","Vlissingen"};
    private final int MIN_SHIP_ID = 1000000;
    private final int MAX_SHIP_ID = 9999999;
    private final int MAX_DISTANCE_TO_LOADING_DOCK = 80000;

    public PositionMessage(){

    }

    public PositionMessage(String frequency) {
        this.setRandomMessage();
        this.delay = frequency;
    }

    public PositionMessage(String shipId, String delay, String stationId, String distanceToLoadingDock) {
        this.shipId = shipId;
        this.delay = delay;
        this.stationId = stationId;
        this.distanceToLoadingDock = distanceToLoadingDock;
        this.timestamp = new Date();
    }

    public String getShipId() {
        return this.shipId;
    }

    public String getStationId() {
        return this.stationId;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getDistanceToLoadingDock() {
        return this.distanceToLoadingDock;
    }

    public String getDelay() {
        return this.delay;
    }

    private void setRandomMessage(){
        Random random = new Random();
        for(String shipId : shipIds){
            shipId = Integer.toString(random.nextInt(this.MAX_SHIP_ID - this.MIN_SHIP_ID) + this.MIN_SHIP_ID);
        }
        this.shipId = shipIds[random.nextInt(this.LIST_SIZE)];
        this.stationId = centraleIds[random.nextInt(this.LIST_SIZE)];
        this.distanceToLoadingDock = Integer.toString(random.nextInt(this.MAX_DISTANCE_TO_LOADING_DOCK));
        this.timestamp = new Date();
    }
}
