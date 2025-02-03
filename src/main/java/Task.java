public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public abstract String serializeTask(Task task);

    public static Task deserializeTask(String line) throws TimitomoException {
        try {
            String[] args = line.split(" \\| ");
            TaskType type = TaskType.fromId(args[0]);
            boolean isDone = args[1].equals("1");
            return type.deserializeTask(args, isDone);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new TimitomoException("Error: corrupted text.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type.getId(), getStatusIcon(), description);
    }
}
