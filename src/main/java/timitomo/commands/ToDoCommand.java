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
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TimitomoException {
        Task task = new ToDo(description, false);
        tasks.addTask(task);
        storage.saveTasks(tasks);
        return Ui.getAddCommandResponse(task, tasks.size());
    }
}
