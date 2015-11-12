package be.kdg.schelderadarchain.processor.eta.strategy.calculation;

import be.kdg.schelderadarchain.processor.eta.model.Eta;
import be.kdg.schelderadarchain.processor.model.Position;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents a strategy to calculate ETA.
 *
 * In this case, calculations aren't very precise.
 */
public class RoughMethod implements EtaCalculationMethod {
    public RoughMethod() { }

    @Override
    public Eta calculateEta(List<Position> positions) {
        if (positions.size() < 2) { return null; }

        int lastIndex = positions.size() - 1;

        // calculate # time passed
        LocalDateTime time1 = positions.get(lastIndex - 1).getTimestamp();
        LocalDateTime time2 = positions.get(lastIndex).getTimestamp();
        Duration duration = Duration.between(time1, time2);

        // calculate # distance covered
        long previousDistance = positions.get(lastIndex - 1).getDistanceToLoadingDock();
        long currentDistance = positions.get(lastIndex).getDistanceToLoadingDock();
        long distanceCovered = (previousDistance <= currentDistance) ?
                currentDistance - previousDistance : previousDistance - currentDistance;

        duration = this.calculateEta(currentDistance, distanceCovered, duration.getSeconds());

        return new Eta(duration);
    }

    private Duration calculateEta(long distanceToCover, long distanceCovered, long seconds) {
        distanceCovered = (distanceCovered == 0) ? 1 : distanceCovered;
        return Duration.ofSeconds(distanceToCover * seconds / distanceCovered);
    }
}
