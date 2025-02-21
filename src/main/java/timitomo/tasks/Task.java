package timitomo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import timitomo.enums.TaskType;
import timitomo.exceptions.TimitomoException;

/**
 * Represents a generic task in the task system, containing common functionalities for all task types.
 */
public abstract class Task {
    protected static final DateTimeFormatter FORMAT_TEXT = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HHmm", Locale.ENGLISH);
    protected static final DateTimeFormatter FORMAT_PRINT = DateTimeFormatter.ofPattern(
            "dd MMM yyyy HHmm", Locale.ENGLISH);
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a new {@code Task}.
     *
     * @param description The task description.
     * @param isDone Whether the task has been completed.
     * @param type The task type.
     */
    protected Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Converts a string representation of date and time to a {@code LocalDateTime} object.
     *
     * @param dateTime The date and time string to be parsed.
     * @param defaultTime The time to be used if the user input does not provide a time.
     * @return A {@code LocalDateTime} object representing the date and time.
     * @throws TimitomoException If the input string format is invalid.
     */
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

    /**
     * Returns the status icon of the task.
     *
     * @return An "X" if the task is done, a whitespace character otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Serializes the task into a string for storage.
     *
     * @return A string representation of the task.
     */
    public String serializeTask() {
        return String.format("%s | %d | %s", type, isDone ? 1 : 0, description);
    }

    /**
     * Deserializes the task from its stored string representation.
     *
     * @param line The stored string representation of the task.
     * @return A {@code Task} object.
     * @throws TimitomoException If the stored task is corrupted.
     */
    public static Task deserializeTask(String line) throws TimitomoException {
        try {
            String[] args = line.split(" \\| ");
            TaskType type = TaskType.valueOf(args[0]);
            boolean isDone = switch (args[1]) {
                case "0" -> false;
                case "1" -> true;
                default -> throw new TimitomoException("Error: corrupted task status.");
            };
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
