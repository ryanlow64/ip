package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.Deadline;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

/**
 * Represents a command that adds a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs a {@code DeadlineCommand} with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the deadline command, adding a deadline task to the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TimitomoException {
        Task task = new Deadline(description, false, by);
        tasks.addTask(task);
        storage.saveTasks(tasks);
        return Ui.getAddCommandResponse(task, tasks.size());
    }
}
