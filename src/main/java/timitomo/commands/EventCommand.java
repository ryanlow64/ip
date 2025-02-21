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
    private final String from;
    private final String to;

    /**
     * Constructs an {@code EventCommand} with the description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command, adding an event task to the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TimitomoException {
        Task task = new Event(description, false, from, to);
        tasks.addTask(task);
        storage.saveTasks(tasks);
        return Ui.getAddCommandResponse(task, tasks.size());
    }
}
