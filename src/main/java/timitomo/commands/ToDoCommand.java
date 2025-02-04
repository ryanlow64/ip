package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;
import timitomo.tasks.ToDo;
import timitomo.ui.Ui;

public class ToDoCommand extends Command {
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TimitomoException {
        Task task = new ToDo(description, false);
        tasks.addTask(task);
        ui.printAddCommand(task, tasks.size());
        storage.saveTasks(tasks);
    }
}
