package timitomo.storage;

import timitomo.exceptions.TimitomoException;
import timitomo.tasks.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/timitomo.txt";
    public static void saveTasks(ArrayList<Task> tasks) throws TimitomoException {
        try {
            File file = new File(FILE_PATH);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.serializeTask() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new TimitomoException("Error saving tasks: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() throws TimitomoException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
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
