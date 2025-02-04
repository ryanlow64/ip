public class ToDo extends Task {
    protected ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }
}
