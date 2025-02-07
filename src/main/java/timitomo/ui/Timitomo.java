package timitomo.ui;

import timitomo.commands.Command;
import timitomo.exceptions.TimitomoException;
import timitomo.parser.Parser;
import timitomo.storage.Storage;
import timitomo.tasks.TaskList;

/**
 * The main class for the Timitomo chatbot with the entry point.
 */
public class Timitomo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code Timitomo} instance and initializes the storage, UI, and task list.
     * @param filePath The file path to retrieve and store tasks.
     */
    public Timitomo(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (TimitomoException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Greets the user and continuously reads and executes user commands.
     */
    public void run() {
        ui.greet();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (TimitomoException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point to start the Timitomo application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Timitomo("./data/timitomo.txt").run();
    }
}
