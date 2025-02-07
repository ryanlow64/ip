package timitomo.tasks;

import timitomo.exceptions.TimitomoException;
import timitomo.enums.TaskType;

import java.time.LocalDateTime;

/**
 * Represents a task with a start date and time, and an end date and time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Creates a new {@code Event} task.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is completed.
     * @param start The starting date and time of the task.
     * @param end The ending date and time of the task.
     * @throws TimitomoException If the date and time format is invalid, or the end is before the start.
     */
    public Event(String description, boolean isDone, String start, String end) throws TimitomoException {
        super(description, isDone, TaskType.EVENT);
        this.start = parseDateTime(start, "0000");
        this.end = parseDateTime(end, "2359");
        if (this.end.isBefore(this.start)) {
            throw new TimitomoException("End time cannot be before start time!");
        }
    }

    @Override
    public String serializeTask() {
        return String.format("%s | %s | %s",
                super.serializeTask(), start.format(FORMAT_TEXT), end.format(FORMAT_TEXT));
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)",
                super.toString(), start.format(FORMAT_PRINT), end.format(FORMAT_PRINT));
    }
}
