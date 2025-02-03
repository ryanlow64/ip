public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    @Override
    public String serializeTask(Task task) {
        return String.format("%s | %d | %s | %s | %s", TaskType.EVENT, super.isDone ? 1 : 0, super.description, start, end);
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), start, end);
    }
}
