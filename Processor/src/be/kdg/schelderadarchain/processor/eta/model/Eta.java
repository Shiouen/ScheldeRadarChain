package be.kdg.schelderadarchain.processor.eta.model;

import java.time.Duration;

/**
 * This model class represents an ETA within the processor.
 *
 * @author Olivier Van Aken
 */
public class Eta {
    private long days;
    private long hours;
    private long minutes;
    private long seconds;

    public Eta(Duration duration) {
        this.days = duration.toDays();
        this.hours = duration.toHours() - 24 * this.days;
        this.minutes = duration.toMinutes() - 60 * duration.toHours();
        this.seconds = duration.getSeconds() - 60 * duration.toMinutes();
    }

    public long getDays() { return this.days; }
    public long getHours() { return this.hours; }
    public long getMinutes() { return this.minutes; }
    public long getSeconds() { return this.seconds; }

    @Override
    public String toString() {
        String eta = "Eta { days : %s, hours : %s, minutes : %s, seconds : %s }";
        return String.format(eta, this.days, this.hours, this.minutes, this.seconds);
    }
}
