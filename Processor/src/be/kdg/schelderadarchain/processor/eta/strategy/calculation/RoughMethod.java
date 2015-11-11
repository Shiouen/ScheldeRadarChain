package be.kdg.schelderadarchain.processor.eta.strategy.calculation;

import be.kdg.schelderadarchain.processor.model.Position;

import java.sql.Timestamp;
import java.util.List;

public class RoughMethod implements EtaCalculationMethod {
    public RoughMethod() { }

    @Override
    public Timestamp calculateEta(List<Position> positions) {
        if (positions.size() < 2) { return null; }

        int lastIndex = positions.size() - 1;

        Timestamp time1 = positions.get(lastIndex - 1).getTimestamp();
        Timestamp time2 = positions.get(lastIndex).getTimestamp();

        long previousDistance = positions.get(lastIndex - 1).getDistanceToLoadingDock();
        long currentDistance = positions.get(lastIndex).getDistanceToLoadingDock();

        long distanceCovered = (previousDistance <= currentDistance) ?
                currentDistance - previousDistance : previousDistance - currentDistance;
        long time = this.calculateTime(currentDistance, distanceCovered, time2.getTime() - time1.getTime());

        return new Timestamp(time);
    }

    private long calculateTime(long distanceToCover, long distanceCovered, long timeDifference) {
        return distanceToCover * timeDifference / distanceCovered;
    }
}
