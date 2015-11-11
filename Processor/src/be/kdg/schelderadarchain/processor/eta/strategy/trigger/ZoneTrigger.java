package be.kdg.schelderadarchain.processor.eta.strategy.trigger;

import java.util.List;

import be.kdg.schelderadarchain.processor.model.Position;

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
