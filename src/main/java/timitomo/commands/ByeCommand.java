package timitomo.commands;

import timitomo.storage.Storage;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printText("Goodbye!");
        System.exit(0);
    }
}
