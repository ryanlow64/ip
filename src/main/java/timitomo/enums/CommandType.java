package timitomo.enums;

import timitomo.exceptions.TimitomoException;

/**
 * Represents the types of executable commands for the chatbot.
 */
public enum CommandType {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

    /**
     * Converts a string representation of a command to a {@code CommandType} enum.
     *
     * @param type The string representation of a command.
     * @return The corresponding {@code CommandType}.
     * @throws TimitomoException If the command is invalid.
     */
    public static CommandType getAction(String type) throws TimitomoException {
        try {
            return CommandType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TimitomoException("I don't know what that means");
        }
    }
}
