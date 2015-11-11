package be.kdg.schelderadarchain.generator.dom;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by Cas on 11/11/2015.
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
