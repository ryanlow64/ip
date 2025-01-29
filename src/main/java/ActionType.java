public enum ActionType {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

    public static ActionType getAction(String type) throws TimitomoException {
        try {
            return ActionType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TimitomoException("I don't know what that means");
        }
    }
}
