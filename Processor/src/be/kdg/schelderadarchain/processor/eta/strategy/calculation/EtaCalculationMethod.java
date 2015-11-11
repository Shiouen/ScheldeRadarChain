package be.kdg.schelderadarchain.processor.eta.strategy.calculation;

import java.sql.Timestamp;
import java.util.List;

import be.kdg.schelderadarchain.processor.model.Position;

public interface EtaCalculationMethod {
    Timestamp calculateEta(List<Position> positions);
}
