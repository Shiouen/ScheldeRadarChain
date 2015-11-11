package be.kdg.schelderadarchain.processor.buffer;

public interface Buffer<T1, T2> {
    boolean isBuffered(T1 key);
    void buffer(T2 value);
    void clear(T1 key);
}
