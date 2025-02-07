package timitomo.storage;

import timitomo.tasks.Task;
import timitomo.tasks.TaskList;

import java.util.ArrayList;

/**
 * A stub version of the {@code Storage} class for testing purposes.
 * The storage methods are overridden to so that no file I/O operations occur.
 */
public class StorageStub extends Storage {

    public StorageStub() {
        super("");
    }

    @Override
    public void saveTasks(TaskList tasks) {}

    @Override
    public ArrayList<Task> loadTasks() {
        return new ArrayList<>();
    }
}
