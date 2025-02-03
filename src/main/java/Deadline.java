public class Deadline extends Task {
    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String serializeTask(Task task) {
        return String.format("%s | %d | %s | %s", TaskType.DEADLINE, super.isDone ? 1 : 0, super.description, by);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }
}
