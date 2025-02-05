package timitomo.storage;

import timitomo.exceptions.TimitomoException;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(TaskList tasks) throws TimitomoException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks.getAllTasks()) {
                writer.write(task.serializeTask() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new TimitomoException("Error saving tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() throws TimitomoException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            int lineNumber = 1;
            while (line != null) {
                try {
                    tasks.add(Task.deserializeTask(line));
                } catch (TimitomoException e) {
                    System.err.printf("%s [line %d]%n", e.getMessage(), lineNumber);
                }
                line = reader.readLine();
                lineNumber++;
            }
        } catch (IOException e) {
            throw new TimitomoException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
