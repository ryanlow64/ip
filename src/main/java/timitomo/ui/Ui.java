package timitomo.ui;

import timitomo.exceptions.TimitomoException;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void printText(String text) {
        for (String line : text.split(System.lineSeparator())) {
            System.out.println(">>> " + line);
        }
        System.out.println("----------------");
    }

    public void printListCommand(TaskList taskList) throws TimitomoException {
        ArrayList<Task> tasks = taskList.getAllTasks();
        if (tasks.isEmpty()) {
            throw new TimitomoException("Nothing to do in task list. Stop slacking off!");
        }
        System.out.printf(">>> You have %d %s in the list. What are you waiting for?%n",
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf(">>> %d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println("----------------");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void greet() {
        printText(String.format("Hello! I'm Timitomo.%nWhat can I do for you?"));
    }

    public void printError(String errorMsg) {
        for (String line : errorMsg.split(System.lineSeparator())) {
            System.out.println(">>> " + line);
        }
        System.out.println("----------------");
    }

    public void printAddCommand(Task task, int taskCount) {
        printText(String.format("I've added this task:%n    %s%nYou have %d %s in the list.",
                task.toString(), taskCount, taskCount == 1 ? "task" : "tasks"));
    }

    public void printDeleteCommand(Task task, int taskCount) {
        printText(String.format("Alright, I've deleted this task:%n    %s%nYou have %d %s in the list.",
                task.toString(), taskCount, taskCount == 1 ? "task" : "tasks"));
    }

    public void printMarkCommand(Task task) {
        printText(String.format("Nice! I've marked it as done:%n    %s", task.toString()));
    }

    public void printUnmarkCommand(Task task) {
        printText(String.format("I've marked it as not done yet. Get to work!%n    %s", task.toString()));
    }
}
