package be.kdg.schelderadarchain.generator.dom;

import java.util.Collection;

/**
 * Created by Cas on 11/11/2015.
 */
public class Route {

    private String shipId;
    private Collection<PositionMessage> positionMessages;

    public Route(String shipId, Collection<PositionMessage> positionMessages) {
        this.shipId = shipId;
        this.positionMessages = positionMessages;
    }

    public String getShipId() {
        return shipId;
    }
    public Collection<PositionMessage> getPositionMessages() {
        return positionMessages;
    }
}
