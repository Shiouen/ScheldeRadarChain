package be.kdg.schelderadarchain.processor.buffer;

import java.util.HashMap;
import java.util.List;

public abstract class Buffer<T1, T2> {
    protected HashMap<T1, List<T2>> buffer;

    protected Buffer() {
        this.buffer = new HashMap<>();
    }

    public abstract void buffer(T2 value);

    public final void clear(T1 key) {
        this.buffer.get(key).clear();
    }
}
