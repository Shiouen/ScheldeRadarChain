package be.kdg.schelderadarchain.generator.backend.dom;

import java.util.Collection;

/**
 * Entity representing a route
 *
 * @author Cas Decelle
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
