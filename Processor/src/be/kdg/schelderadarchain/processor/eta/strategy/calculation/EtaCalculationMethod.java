package be.kdg.schelderadarchain.processor.eta.strategy.calculation;

import java.util.List;

import be.kdg.schelderadarchain.processor.eta.model.Eta;
import be.kdg.schelderadarchain.processor.model.Position;

public interface EtaCalculationMethod {
    Eta calculateEta(List<Position> positions);
}
