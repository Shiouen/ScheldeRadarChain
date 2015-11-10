package be.kdg.schelderadarchain.processor.buffer.schedule;

import java.util.HashMap;
import java.util.Timer;

import be.kdg.schelderadarchain.processor.buffer.Buffer;
import be.kdg.schelderadarchain.processor.buffer.properties.BufferProperties;

public final class BufferScheduleFactory {
    private final static HashMap<Object, BufferSchedule> schedules;

    static {
        schedules = new HashMap<>();
    }

    private BufferScheduleFactory() { }

    public final static void schedule(Buffer buffer, Object key) {
        BufferSchedule schedule = schedules.get(key);
        if (schedule != null) {
            schedule.cancel();
        }

        Timer timer = new Timer();
        schedule = new BufferSchedule(buffer, key, timer);

        schedules.put(key, schedule);
        timer.schedule(new BufferSchedule(buffer, key, timer), BufferProperties.getBufferDuration());
    }
}
