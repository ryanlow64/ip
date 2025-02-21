package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

/**
 * Represents a command that marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} instance with the given index.
     *
     * @param index The index of the task to mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command, marking a task as not done.
     *
     * @param tasks The task list containing all the tasks.
     * @param storage The storage handler saving the task list to the hard disk.
     * @throws TimitomoException If an error occurs when executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TimitomoException {
        tasks.unmarkTask(index);
        storage.saveTasks(tasks);
        return Ui.getUnmarkCommandResponse(tasks.getTask(index));
    }
}
