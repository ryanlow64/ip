package timitomo.ui;

import java.util.ArrayList;

import timitomo.tasks.Task;

/**
 * Manages interaction with the user.
 */
public class Ui {
    /**
     * Returns a message with relevant details when a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The updated total number of tasks.
     */
    public static String getAddCommandResponse(Task task, int taskCount) {
        return String.format("I've added this task:%n    %s%nYou have %d %s in the list.",
                task.toString(), taskCount, taskCount == 1 ? "task" : "tasks");
    }

    /**
     * Returns a message with relevant details when a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The updated total number of tasks.
     */
    public static String getDeleteCommandResponse(Task task, int taskCount) {
        return String.format("Alright, I've deleted this task:%n    %s%nYou have %d %s in the list.",
                task.toString(), taskCount, taskCount == 1 ? "task" : "tasks");
    }

    /**
     * Returns the list of tasks stored in the {@code ArrayList} object for the find command.
     *
     * @param tasks The {@code ArrayList} object containing the tasks.
     */
    public static String getFindCommandResponse(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found!";
        }
        return String.format("%d matching %s found in your list.%n%s",
                tasks.size(), tasks.size() == 1 ? "task" : "tasks", tasksToText(tasks));
    }

    /**
     * Returns the list of tasks stored in the {@code ArrayList} object for the list command.
     *
     * @param tasks The {@code ArrayList} object containing the tasks.
     */
    public static String getListCommandResponse(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Nothing to do in task list. Stop slacking off!";
        }
        return String.format("You have %d %s in the list. What are you waiting for?%n%s",
                tasks.size(), tasks.size() == 1 ? "task" : "tasks", tasksToText(tasks));
    }

    /**
     * Returns a message with relevant details when a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public static String getMarkCommandResponse(Task task) {
        return String.format("Nice! I've marked it as done:%n    %s", task.toString());
    }

    /**
     * Returns a message with relevant details when a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public static String getUnmarkCommandResponse(Task task) {
        return String.format("I've marked it as not done yet. Get to work!%n    %s", task.toString());
    }

    private static String tasksToText(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s%n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }
}
