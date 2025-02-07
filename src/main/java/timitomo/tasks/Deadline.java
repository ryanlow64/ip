package timitomo.tasks;

import timitomo.enums.TaskType;
import timitomo.exceptions.TimitomoException;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a new {@code Deadline} task.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is completed.
     * @param by The deadline of the task.
     * @throws TimitomoException If the deadline format is invalid.
     */
    public Deadline(String description, boolean isDone, String by) throws TimitomoException {
        super(description, isDone, TaskType.DEADLINE);
        this.by = parseDateTime(by, "2359");
    }

    @Override
    public String serializeTask() {
        return String.format("%s | %s", super.serializeTask(), by.format(FORMAT_TEXT));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by.format(FORMAT_PRINT));
    }
}
