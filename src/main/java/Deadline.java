import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, boolean isDone, String by) throws TimitomoException {
        super(description, isDone, TaskType.DEADLINE);
        try {
            this.by = LocalDateTime.parse(by, FORMAT_TEXT);
        } catch (DateTimeParseException e) {
            throw new TimitomoException(String.format(
                    "%s%nUse dd-mm-yyyy hh:mm (e.g. 20-04-2025 1744)", e.getMessage()));
        }
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
