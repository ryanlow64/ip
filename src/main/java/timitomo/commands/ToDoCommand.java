package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;
import timitomo.tasks.ToDo;
import timitomo.ui.Ui;

/**
 * Represents a command that adds a todo task.
 */
public class ToDoCommand extends Command {
    private final String description;

    /**
     * Constructs a {@code ToDoCommand} instance with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command, adding a todo task to the task list.
     *
     * @param tasks The task list containing all the tasks.
     * @param ui The UI handler for reading user inputs and printing messages.
     * @param storage The storage handler saving the task list to the hard disk.
     * @throws TimitomoException If an error occurs when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TimitomoException {
        Task task = new ToDo(description, false);
        tasks.addTask(task);
        ui.printAddCommand(task, tasks.size());
        storage.saveTasks(tasks);
    }
}
