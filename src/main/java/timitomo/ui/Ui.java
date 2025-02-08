package timitomo.ui;

import timitomo.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages user interaction with the Timitomo application.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a {@code Ui} instance and initializes an input scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the specified text with formatting.
     * Each line is prefixed with ">>>", with a separator at the end of the message.
     *
     * @param text The text to be printed.
     */
    public void printText(String text) {
        for (String line : text.split(System.lineSeparator())) {
            System.out.println(">>> " + line);
        }
        System.out.println("----------------");
    }

    /**
     * Prints the list of tasks stored in the {@code ArrayList} object for the find command.
     *
     * @param tasks The {@code ArrayList} object containing the tasks.
     */
    public void printFindCommand(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            printText("No matching tasks found!");
            return;
        }
        System.out.printf(">>> %d matching %s found in your list.%n",
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
        printTasks(tasks);
    }

    /**
     * Prints the list of tasks stored in the {@code ArrayList} object for the list command.
     *
     * @param tasks The {@code ArrayList} object containing the tasks.
     */
    public void printListCommand(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            printText("Nothing to do in task list. Stop slacking off!");
            return;
        }
        System.out.printf(">>> You have %d %s in the list. What are you waiting for?%n",
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
        printTasks(tasks);
    }

    private void printTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf(">>> %d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println("----------------");
    }

    /**
     * Reads an input from the user.
     *
     * @return The trimmed user input.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Greets the user when the application starts.
     */
    public void greet() {
        printText(String.format("Hello! I'm Timitomo.%nWhat can I do for you?"));
    }

    /**
     * Prints an error message with formatting.
     *
     * @param errorMsg The error message to be printed.
     */
    public void printError(String errorMsg) {
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";
        for (String line : errorMsg.split(System.lineSeparator())) {
            System.out.println(">>> " + RED + line + RESET);
        }
        System.out.println("----------------");
    }

    /**
     * Prints a message with relevant details when a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The updated total number of tasks.
     */
    public void printAddCommand(Task task, int taskCount) {
        printText(String.format("I've added this task:%n    %s%nYou have %d %s in the list.",
                task.toString(), taskCount, taskCount == 1 ? "task" : "tasks"));
    }

    /**
     * Prints a message with relevant details when a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The updated total number of tasks.
     */
    public void printDeleteCommand(Task task, int taskCount) {
        printText(String.format("Alright, I've deleted this task:%n    %s%nYou have %d %s in the list.",
                task.toString(), taskCount, taskCount == 1 ? "task" : "tasks"));
    }

    /**
     * Prints a message with relevant details when a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarkCommand(Task task) {
        printText(String.format("Nice! I've marked it as done:%n    %s", task.toString()));
    }

    /**
     * Prints a message with relevant details when a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printUnmarkCommand(Task task) {
        printText(String.format("I've marked it as not done yet. Get to work!%n    %s", task.toString()));
    }
}
