package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TimitomoException {
        ui.printListCommand(tasks.getAllTasks());
    }
}
