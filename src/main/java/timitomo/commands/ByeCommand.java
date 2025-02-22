package timitomo.commands;

import timitomo.storage.Storage;
import timitomo.tasks.TaskList;

/**
 * Represents a command that terminates the chatbot.
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command, terminating the chatbot application.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Goodbye!";
    }
}
