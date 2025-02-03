public class ToDo extends Task {
    protected ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    @Override
    public String serializeTask() {
        return String.format("%s | %d | %s", super.type, super.isDone ? 1 : 0, super.description);
    }
}
