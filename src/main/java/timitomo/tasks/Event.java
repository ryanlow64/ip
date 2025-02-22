package timitomo.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

import timitomo.enums.TaskType;
import timitomo.exceptions.TimitomoException;

/**
 * Represents a task with a start date and time, and an end date and time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected ArrayList<LocalDateTime[]> tentativeSlots;

    /**
     * Creates a new {@code Event} task with a confirmed slot.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is completed.
     * @param start The starting date and time of the task.
     * @param end The ending date and time of the task.
     */
    public Event(String description, boolean isDone, String start, String end) throws TimitomoException {
        super(description, isDone, TaskType.EVENT);
        this.start = parseDateTime(start, "0000");
        this.end = parseDateTime(end, "2359");
        if (this.end.isBefore(this.start)) {
            throw new TimitomoException("End time cannot be before start time!");
        }
        this.tentativeSlots = new ArrayList<>();
    }

    /**
     * Creates a new {@code Event} task with a multiple tentative slots.
     *
     * @param description The description of the task.
     * @param tentativeSlots The tentative slots for the event.
     */
    public Event(String description, String[]... tentativeSlots) throws TimitomoException {
        super(description, false, TaskType.EVENT);
        this.tentativeSlots = new ArrayList<>();

        for (String[] slot : tentativeSlots) {
            if (slot.length != 2) {
                throw new TimitomoException("Each slot must have a start and end time.");
            }
            LocalDateTime start = parseDateTime(slot[0], "0000");
            LocalDateTime end = parseDateTime(slot[1], "2359");
            if (end.isBefore(start)) {
                throw new TimitomoException("End time cannot be before start time!");
            }
            this.tentativeSlots.add(new LocalDateTime[] {start, end});
        }
    }

    /**
     * Confirms a slot for the event schedule.
     */
    public void confirmSlot(int index) throws TimitomoException {
        if (tentativeSlots.isEmpty()) {
            throw new TimitomoException("Event already has a confirmed slot");
        } else if (index < 0 || index >= tentativeSlots.size()) {
            throw new TimitomoException("Invalid slot number.");
        }

        LocalDateTime[] slot = tentativeSlots.get(index);
        start = slot[0];
        end = slot[1];
        tentativeSlots.clear();
    }

    @Override
    public void markAsDone() {
        if (!tentativeSlots.isEmpty()) {
            throw new IllegalStateException("Confirm a slot first before marking the event as done.");
        }
        super.markAsDone();
    }

    @Override
    public String serializeTask() {
        if (tentativeSlots.isEmpty()) {
            return String.format("%s | %s - %s",
                    super.serializeTask(), start.format(FORMAT_TEXT), end.format(FORMAT_TEXT));
        } else {
            StringBuilder sb = new StringBuilder(super.serializeTask());
            for (LocalDateTime[] slot : tentativeSlots) {
                sb.append(String.format(" | %s - %s", slot[0].format(FORMAT_TEXT), slot[1].format(FORMAT_TEXT)));
            }
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        if (tentativeSlots.isEmpty()) {
            return String.format("%s (from: %s to: %s)",
                    super.toString(), start.format(FORMAT_PRINT), end.format(FORMAT_PRINT));
        } else {
            StringBuilder sb = new StringBuilder(super.toString());
            sb.append(" (Tentative Slots:");
            for (int i = 0; i < tentativeSlots.size(); i++) {
                LocalDateTime[] slot = tentativeSlots.get(i);
                sb.append(String.format(" [slot %d: %s - %s]",
                        i + 1, slot[0].format(FORMAT_PRINT), slot[1].format(FORMAT_PRINT)));
            }
            sb.append(")");
            return sb.toString();
        }
    }
}
