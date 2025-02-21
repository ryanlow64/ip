package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

/**
 * Represents a command that lists all the tasks that contains a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the given keyword.
     *
     * @param keyword The keyword to find in the task description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, listing all the tasks containing the keyword.
     *
     * @param tasks The task list containing all the tasks.
     * @param storage The storage handler saving the task list to the hard disk.
     * @throws TimitomoException If an error occurs when executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TimitomoException {
        return Ui.getFindCommandResponse(tasks.findTasks(keyword));
    }
}
