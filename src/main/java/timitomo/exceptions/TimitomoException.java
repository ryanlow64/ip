package timitomo.exceptions;

/**
 * Represents a custom exception for the chatbot.
 */
public class TimitomoException extends Exception {
    /**
     * Constructs a new {@code TimitomoException} with the specified message.
     *
     * @param message The message describing the exception.
     */
    public TimitomoException(String message) {
        super(message);
    }
}
