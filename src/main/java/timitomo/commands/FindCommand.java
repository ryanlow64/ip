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
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws TimitomoException {
        return Ui.getFindCommandResponse(taskList.findTasks(keyword));
    }
}
