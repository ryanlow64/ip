package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

/**
 * Represents a command that deletes a task.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a {@code DeleteCommand} with the given index.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command, deleting a task from the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TimitomoException {
        Task task = tasks.deleteTask(index);
        storage.saveTasks(tasks);
        return Ui.getDeleteCommandResponse(task, tasks.size());
    }
}
