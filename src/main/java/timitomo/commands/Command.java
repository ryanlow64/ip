package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.TaskList;

/**
 * Represents a base class for executable commands with relevant arguments.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList The task list containing all the taskList.
     * @param storage The storage handler saving the task list to the hard disk.
     * @return The response to the user.
     * @throws TimitomoException If an error occurs when executing the command.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws TimitomoException;
}
