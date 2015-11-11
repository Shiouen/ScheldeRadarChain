package be.kdg.schelderadarchain.processor.buffer.schedule;

import java.util.HashMap;
import java.util.Timer;

import be.kdg.schelderadarchain.processor.buffer.Buffer;

public final class BufferScheduleFactory {
    private final HashMap<Object, BufferSchedule> schedules;

    public BufferScheduleFactory() {
        this.schedules = new HashMap<>();
    }

    public final void schedule(Buffer buffer, Object key, Integer duration) {
        BufferSchedule schedule = this.schedules.get(key);
        if (schedule != null) {
            schedule.cancel();
        }

        Timer timer = new Timer();
        schedule = new BufferSchedule(buffer, key, timer);

        this.schedules.put(key, schedule);
        timer.schedule(new BufferSchedule(buffer, key, timer), duration);
    }
}
