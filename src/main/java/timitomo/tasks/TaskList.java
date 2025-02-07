package timitomo.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates the task list from the specified list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     * @throws IllegalArgumentException If the index is invalid.
     */
    public Task deleteTask(int index) throws IllegalArgumentException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        return tasks.remove(index);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index The index of the task to mark as done.
     * @throws IllegalArgumentException If the index is invalid.
     */
    public void markTask(int index) throws IllegalArgumentException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Marks a task in the list as not done.
     *
     * @param index The index of the task to mark as not done.
     * @throws IllegalArgumentException If the index is invalid.
     */
    public void unmarkTask(int index) throws IllegalArgumentException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        tasks.get(index).markAsNotDone();
    }

    /**
     * Returns all the tasks in the list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns a task in the list by index.
     *
     * @param index The index of the task.
     * @return The task at the index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

}
