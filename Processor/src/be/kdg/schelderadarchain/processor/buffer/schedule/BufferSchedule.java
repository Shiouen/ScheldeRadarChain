package be.kdg.schelderadarchain.processor.buffer.schedule;

import java.util.Timer;
import java.util.TimerTask;

import be.kdg.schelderadarchain.processor.buffer.Buffer;

public class BufferSchedule extends TimerTask {
    private Buffer buffer;
    private Object key;
    private Timer timer;

    public BufferSchedule(Buffer buffer, Object key, Timer timer) {
        this.buffer = buffer;
        this.key = key;
        this.timer = timer;
    }

    @Override
    public void run() {
        this.timer.cancel();
        this.buffer.clear(this.key);
    }

    @Override
    public boolean cancel() {
        this.timer.cancel();
        return super.cancel();
    }
}
