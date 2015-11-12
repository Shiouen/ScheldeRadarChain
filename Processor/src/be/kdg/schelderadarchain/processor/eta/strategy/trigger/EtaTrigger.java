package be.kdg.schelderadarchain.processor.eta.strategy.trigger;

import java.util.List;

import be.kdg.schelderadarchain.processor.model.Position;

/**
 * This interface represents a strategic trigger for when ETA should be calculated.
 *
 * @author Olivier Van Aken
 */
public interface EtaTrigger {
    /**
     * This method defines how to know it the trigger triggers.
     *
     * @param positions List of Positions used to know whether the trigger triggers.
     * @return true In case the trigger triggers.
     */
    boolean isTriggeredBy(List<Position> positions);
}
