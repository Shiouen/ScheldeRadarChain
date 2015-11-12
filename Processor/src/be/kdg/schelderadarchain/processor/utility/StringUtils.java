package be.kdg.schelderadarchain.processor.utility;

/**
 * String utility class
 *
 * @author Olivier Van Aken
 */
public final class StringUtils {
    private StringUtils() { }

    public final static String slice(int startIndex, String s) {
        if (startIndex < 0) { startIndex = s.length() + startIndex; }
        return s.substring(startIndex);
    }

    public final static String slice(String s, int endIndex) {
        if (endIndex < 0) { endIndex = s.length() + endIndex; }
        return s.substring(0, endIndex);
    }

    public final static String slice(String s, int startIndex, int endIndex) {
        if (startIndex < 0) { startIndex = s.length() + startIndex; }
        if (endIndex < 0) { endIndex = s.length() + endIndex; }
        return s.substring(startIndex, endIndex);
    }
}
