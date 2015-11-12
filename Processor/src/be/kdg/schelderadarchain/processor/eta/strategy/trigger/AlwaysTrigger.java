package be.kdg.schelderadarchain.processor.eta.strategy.trigger;

import java.util.List;

import be.kdg.schelderadarchain.processor.model.Position;

public class AlwaysTrigger implements EtaTrigger {
    @Override
    public boolean isTriggeredBy(List<Position> positions) {
        return true;
    }
}