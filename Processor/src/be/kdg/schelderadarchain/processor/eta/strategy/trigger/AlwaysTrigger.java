package be.kdg.schelderadarchain.processor.eta.strategy.trigger;

import java.util.List;

import be.kdg.schelderadarchain.processor.model.Position;

/**
 * This EtaTrigger strategy implementation always triggers.
 *
 * @author Olivier Van Aken
 */
public class AlwaysTrigger implements EtaTrigger {
    @Override
    public boolean isTriggeredBy(List<Position> positions) {
        return true;
    }
}