package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.Event;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

/**
 * Represents a command that adds an event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final String[][] slots;

    /**
     * Constructs an {@code EventCommand} with the description, start time, and end time.
     *
     * @param description The description of the task.
     * @param slots The timeslot(s) of the event.
     */
    public EventCommand(String description, String[]... slots) {
        this.description = description;
        this.slots = slots;
    }

    /**
     * Executes the event command, adding an event task to the task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws TimitomoException {
        Task task = slots.length == 1
                ? new Event(description, false, slots[0][0], slots[0][1])
                : new Event(description, slots);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        return Ui.getAddCommandResponse(task, taskList.size());
    }
}
