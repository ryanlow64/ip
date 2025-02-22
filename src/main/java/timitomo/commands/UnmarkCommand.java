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
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws TimitomoException {
        taskList.unmarkTask(index);
        storage.saveTasks(taskList);
        return Ui.getUnmarkCommandResponse(taskList.getTask(index));
    }
}
