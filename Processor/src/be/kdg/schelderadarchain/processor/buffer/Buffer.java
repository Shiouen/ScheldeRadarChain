package be.kdg.schelderadarchain.processor.buffer;

/**
 * This interface defines how a buffer will be buffered and cleared of its generic <T> objects
 *
 * @param <T1> key
 * @param <T2> value
 * @author Olivier Van Aken
 */
public interface Buffer<T1, T2> {
    /**
     * Defines how to know whether a key is already used to buffer a generic object <T>.
     *
     * @param key Key to check with.
     * @return true if the key is already used and thus an object is buffered.
     */
    boolean isBuffered(T1 key);

    /**
     * Defines how to buffer a generic object <T>.
     *
     * @param value The generic object <T>.
     */
    void buffer(T2 value);

    /**
     * Defines how a generic object <T> is cleared from the buffer using its key.
     *
     * @param key Key to clear a corresponding generic object <T> from the buffer.
     */
    void clear(T1 key);
}
