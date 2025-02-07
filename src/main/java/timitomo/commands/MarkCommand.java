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
     *
     * @param tasks The task list containing all the tasks.
     * @param ui The UI handler for reading user inputs and printing messages.
     * @param storage The storage handler saving the task list to the hard disk.
     * @throws TimitomoException If an error occurs when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TimitomoException {
        tasks.markTask(index);
        ui.printMarkCommand(tasks.getTask(index));
        storage.saveTasks(tasks);
    }
}
