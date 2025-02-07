package timitomo.tasks;

import timitomo.enums.TaskType;

/**
 * Represents a basic task.
 */
public class ToDo extends Task {
    /**
     * Creates a new {@code ToDo} task.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }
}
