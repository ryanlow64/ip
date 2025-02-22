package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a {@code MarkCommand} instance with the given index.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command, marking a task as done.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws TimitomoException {
        taskList.markTask(index);
        storage.saveTasks(taskList);
        return Ui.getMarkCommandResponse(taskList.getTask(index));
    }
}
