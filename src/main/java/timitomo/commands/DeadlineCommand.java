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
     *
     * @param tasks The task list containing all the tasks.
     * @param ui The UI handler for reading user inputs and printing messages.
     * @param storage The storage handler saving the task list to the hard disk.
     * @throws TimitomoException If an error occurs when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TimitomoException {
        Task task = new Deadline(description, false, by);
        tasks.addTask(task);
        ui.printAddCommand(task, tasks.size());
        storage.saveTasks(tasks);
    }
}
