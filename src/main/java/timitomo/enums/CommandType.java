package timitomo.enums;

import timitomo.exceptions.TimitomoException;

public enum CommandType {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND;

    public static CommandType getAction(String type) throws TimitomoException {
        try {
            return CommandType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TimitomoException("I don't know what that means");
        }
    }
}
