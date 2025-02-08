package timitomo.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws IllegalArgumentException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        return tasks.remove(index);
    }

    public void markTask(int index) throws IllegalArgumentException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) throws IllegalArgumentException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        tasks.get(index).markAsNotDone();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Returns a list of tasks that contain the specified keyword (case-insensitive).
     *
     * @param keyword The string to search for in the task descriptions.
     * @return An {@code ArrayList} of tasks whose descriptions contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String keywordLower = keyword.toLowerCase();
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keywordLower)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
