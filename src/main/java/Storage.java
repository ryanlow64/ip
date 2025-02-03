import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/timitomo.txt";
    public static void saveTasks(ArrayList<Task> tasks) throws TimitomoException {
        try {
            File file = new File(FILE_PATH);
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task + System.lineSeparator());
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
            while (line != null) {
                try {
                    tasks.add(Task.deserializeTask(line));
                } catch (TimitomoException e) {
                    System.err.println(e.getMessage());
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new TimitomoException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
