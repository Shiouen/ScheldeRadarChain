package be.kdg.schelderadarchain.processor.eta.strategy.trigger;

import java.util.List;

import be.kdg.schelderadarchain.processor.model.Position;

/**
 * This EtaTrigger strategy implementation triggers when there is a change in distance.
 *
 * @author Olivier Van Aken
 */
public class PositionTrigger implements EtaTrigger {
    @Override
    public boolean isTriggeredBy(List<Position> positions) {
        if (positions.size() < 2) { return false; }

        int lastIndex = positions.size() - 1;

        int distance1 = positions.get(lastIndex - 1).getDistanceToLoadingDock();
        int distance2 = positions.get(lastIndex).getDistanceToLoadingDock();

        return distance1 != distance2;
    }
}
