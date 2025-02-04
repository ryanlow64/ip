package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.Deadline;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TimitomoException {
        Task task = new Deadline(description, false, by);
        tasks.addTask(task);
        ui.printAddCommand(task, tasks.size());
        storage.saveTasks(tasks);
    }
}
