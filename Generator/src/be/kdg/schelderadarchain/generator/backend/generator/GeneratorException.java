package be.kdg.schelderadarchain.generator.backend.generator;

/**
 * Exception class for any exception concerning the Generator.
 *
 * @author Cas Decelle
 */

public class GeneratorException extends RuntimeException {

    public GeneratorException(String message, Throwable cause){
        super(message, cause);
    }
}
