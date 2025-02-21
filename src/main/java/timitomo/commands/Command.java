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
     * @param tasks The task list containing all the tasks.
     * @param storage The storage handler saving the task list to the hard disk.
     * @throws TimitomoException If an error occurs when executing the command.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws TimitomoException;
}
