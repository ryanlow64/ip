package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

/**
 * Represents a command that lists all the tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command, listing all the tasks.
     *
     * @param tasks The task list containing all the tasks.
     * @param storage The storage handler saving the task list to the hard disk.
     * @throws TimitomoException If an error occurs when executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TimitomoException {
        return Ui.getListCommandResponse(tasks.getAllTasks());
    }
}
