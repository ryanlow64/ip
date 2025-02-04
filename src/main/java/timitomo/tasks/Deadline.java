package timitomo.tasks;

import timitomo.enums.TaskType;
import timitomo.exceptions.TimitomoException;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

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
