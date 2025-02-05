package timitomo.storage;

import timitomo.exceptions.TimitomoException;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;

import java.util.ArrayList;

public class StorageStub extends Storage {

    public StorageStub() {
        super("");
    }

    @Override
    public void saveTasks(TaskList tasks) throws TimitomoException {}

    @Override
    public ArrayList<Task> loadTasks() throws TimitomoException {
        return new ArrayList<>();
    }
}

