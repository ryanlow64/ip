package timitomo.commands;

import timitomo.storage.Storage;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

/**
 * Represents a command that terminates the chatbot.
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command, terminating the chatbot application.
     *
     * @param tasks The task list containing all the tasks.
     * @param ui The UI handler for reading user inputs and printing messages.
     * @param storage The storage handler saving the task list to the hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printText("Goodbye!");
        System.exit(0);
    }
}
