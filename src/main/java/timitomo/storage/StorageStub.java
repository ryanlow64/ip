package timitomo.storage;

import timitomo.tasks.Task;
import timitomo.tasks.TaskList;

import java.util.ArrayList;

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

