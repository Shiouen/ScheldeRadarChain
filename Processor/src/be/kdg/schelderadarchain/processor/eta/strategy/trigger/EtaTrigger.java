package be.kdg.schelderadarchain.processor.eta.strategy.trigger;

import java.util.List;

import be.kdg.schelderadarchain.processor.model.Position;

public interface EtaTrigger {
    boolean isTriggeredBy(List<Position> positions);
}
