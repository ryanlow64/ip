package timitomo.ui;

import timitomo.commands.Command;
import timitomo.exceptions.TimitomoException;
import timitomo.enums.CommandType;
import timitomo.parser.Parser;
import timitomo.storage.Storage;
import timitomo.tasks.Deadline;
import timitomo.tasks.Event;
import timitomo.tasks.Task;
import timitomo.tasks.TaskList;
import timitomo.tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Timitomo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Timitomo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (TimitomoException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

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

    public static void main(String[] args) {
        new Timitomo("./data/timitomo.txt").run();
    }
}
