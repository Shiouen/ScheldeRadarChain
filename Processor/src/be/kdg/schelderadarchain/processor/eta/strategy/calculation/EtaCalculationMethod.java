package be.kdg.schelderadarchain.processor.eta.strategy.calculation;

import java.util.List;

import be.kdg.schelderadarchain.processor.eta.model.Eta;
import be.kdg.schelderadarchain.processor.model.Position;

/**
 * This interface represents a strategy to calculate ETA.
 *
 * @author Olivier Van Aken
 */
public interface EtaCalculationMethod {
    /**
     * This method defines how the EtaCalculationMethod can be used to calculate ETA
     *
     * @param positions List of Positions used to calculate the ETA.
     * @return Eta Calculated ETA value.
     */
    Eta calculateEta(List<Position> positions);
}
