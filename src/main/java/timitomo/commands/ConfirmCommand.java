package timitomo.commands;

import timitomo.exceptions.TimitomoException;
import timitomo.storage.Storage;
import timitomo.tasks.Event;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;

/**
 * Represents a command that confirms a slot for an event with tentative slots.
 */
public class ConfirmCommand extends Command {
    private final int taskIndex;
    private final int slotIndex;

    /**
     * Constructs a {@code DeadlineCommand} with the specified task and slot.
     *
     * @param taskIndex The index of the task.
     * @param slotIndex The index of the confirmed slot.
     */
    public ConfirmCommand(int taskIndex, int slotIndex) {
        this.taskIndex = taskIndex;
        this.slotIndex = slotIndex;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TimitomoException {
        Task task = taskList.getTask(taskIndex);
        if (!(task instanceof Event event)) {
            throw new TimitomoException("This selected task is not an event.");
        }
        event.confirmSlot(slotIndex);
        return Ui.getConfirmCommandResponse(event);
    }
}
