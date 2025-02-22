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
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws TimitomoException {
        return Ui.getListCommandResponse(taskList.getAllTasks());
    }
}
