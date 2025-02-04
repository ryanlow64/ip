package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.Event;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TimitomoException {
        Task task = new Event(description, false, from, to);
        tasks.addTask(task);
        ui.printAddCommand(task, tasks.size());
        storage.saveTasks(tasks);
    }
}
