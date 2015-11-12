package be.kdg.schelderadarchain.generator.backend.dom;

import java.util.Date;
import java.util.Map;

/**
 * Entity representing a load schedule for the random load generator
 *
 * @author Cas Decelle
 */

public class LoadSchedule {

    private Map<Date,Date> timeBlocks;
    private int standardFrequency;

    public LoadSchedule(Map<Date,Date> timeBlocks, int standardFrequency) {
        this.timeBlocks = timeBlocks;
        this.standardFrequency = standardFrequency;
    }

    public Map<Date,Date> getTimeBlocks() {
        return timeBlocks;
    }
    public int getStandardFrequency() {
        return standardFrequency;
    }
}
