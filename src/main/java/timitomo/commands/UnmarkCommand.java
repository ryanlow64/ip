package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TimitomoException {
        tasks.unmarkTask(index);
        ui.printUnmarkCommand(tasks.getTask(index));
        storage.saveTasks(tasks);
    }
}
