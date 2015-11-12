package be.kdg.schelderadarchain.processor.eta.strategy.trigger;

import java.util.List;

import be.kdg.schelderadarchain.processor.model.Position;

/**
 * This EtaTrigger strategy implementation triggers when there is a change in zone.
 *
 * @author Olivier Van Aken
 */
public class ZoneTrigger implements EtaTrigger {
    @Override
    public boolean isTriggeredBy(List<Position> positions) {
        if (positions.size() < 2) { return false; }

        int lastIndex = positions.size() - 1;

        String station1 = positions.get(lastIndex - 1).getStationId();
        String station2 = positions.get(lastIndex).getStationId();

        return station1 != station2;
    }
}
