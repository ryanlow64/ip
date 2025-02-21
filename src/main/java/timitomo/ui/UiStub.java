package timitomo.ui;

import java.util.ArrayList;

import timitomo.tasks.Task;

/**
 * A stub version of the {@code Ui} class for testing purposes.
 * The methods are overridden to prevent any I/O operations to the console.
 */
public class UiStub extends Ui {

    public UiStub() {
        super();
    }

    @Override
    public void printText(String text) {}

    @Override
    public void printFindCommand(ArrayList<Task> tasks) {}

    @Override
    public void printListCommand(ArrayList<Task> tasks) {}

    @Override
    public String readCommand() {
        return "";
    }

    @Override
    public void greet() {}

    @Override
    public void printError(String errorMsg) {}

    @Override
    public void printAddCommand(Task task, int taskCount) {}

    @Override
    public void printDeleteCommand(Task task, int taskCount) {}

    @Override
    public void printMarkCommand(Task task) {}

    @Override
    public void printUnmarkCommand(Task task) {}
}
