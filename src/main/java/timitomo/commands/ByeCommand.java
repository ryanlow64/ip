package timitomo.commands;

import timitomo.storage.Storage;
import timitomo.tasks.TaskList;

/**
 * Represents a command that terminates the chatbot.
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command, terminating the chatbot application.
     *
     * @param tasks The task list containing all the tasks.
     * @param storage The storage handler saving the task list to the hard disk.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Goodbye!";
    }
}
