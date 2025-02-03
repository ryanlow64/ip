public class ToDo extends Task {
    protected ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    @Override
    public String serializeTask(Task task) {
        return String.format("%s | %d | %s", TaskType.TODO, super.isDone ? 1 : 0, super.description);
    }
}
