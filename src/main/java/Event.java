import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, boolean isDone, String start, String end) throws TimitomoException {
        super(description, isDone, TaskType.EVENT);
        try {
            this.start = LocalDateTime.parse(start, FORMAT_TEXT);
            this.end = LocalDateTime.parse(end, FORMAT_TEXT);
        } catch (DateTimeParseException e) {
            throw new TimitomoException(String.format(
                    "%s%nUse dd-mm-yyyy hh:mm (e.g. 20-04-2025 1744)", e.getMessage()));
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
