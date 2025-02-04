package timitomo.tasks;

import timitomo.exceptions.TimitomoException;
import timitomo.enums.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public abstract class Task {
    protected static final DateTimeFormatter FORMAT_TEXT = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HHmm", Locale.ENGLISH);
    protected static final DateTimeFormatter FORMAT_PRINT = DateTimeFormatter.ofPattern(
            "dd MMM yyyy HHmm", Locale.ENGLISH);
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    protected Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    protected static LocalDateTime parseDateTime(String dateTime, String defaultTime) throws TimitomoException {
        try {
            return LocalDateTime.parse(dateTime, FORMAT_TEXT);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(dateTime + " " + defaultTime, FORMAT_TEXT);
            } catch (DateTimeParseException e2) {
                throw new TimitomoException(String.format(
                        "%s%nUse dd-mm-yyyy OR dd-mm-yyyy hhmm (e.g. 20-04-2025 1744)", e2.getMessage()));
            }
        }
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

    public String serializeTask() {
        return String.format("%s | %d | %s", type, isDone ? 1 : 0, description);
    }

    public static Task deserializeTask(String line) throws TimitomoException {
        try {
            String[] args = line.split(" \\| ");
            TaskType type = TaskType.valueOf(args[0]);
            boolean isDone;
            switch (args[1]) {
                case "0" -> isDone = false;
                case "1" -> isDone = true;
                default -> throw new TimitomoException("Error: corrupted task status.");
            }
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
