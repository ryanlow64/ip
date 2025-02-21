package timitomo.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import timitomo.commands.ByeCommand;
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
            System.err.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c instanceof ByeCommand) {
                PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }
            return c.execute(tasks, storage);
        } catch (TimitomoException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
