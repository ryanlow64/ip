package timitomo.commands;

import org.junit.jupiter.api.Test;
import timitomo.exceptions.TimitomoException;
import timitomo.parser.Parser;
import timitomo.storage.Storage;
import timitomo.storage.StorageStub;
import timitomo.tasks.TaskList;
import timitomo.ui.Ui;
import timitomo.ui.UiStub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeadlineCommandTest {

    @Test
    void executeTest() throws TimitomoException {
        TaskList tasks = new TaskList();
        Ui ui = new UiStub();
        Storage storage = new StorageStub();
        assertThrows(TimitomoException.class,
                () -> Parser.parse("deadline submit CS2103T /by 20-04-1889 2237 lalalalalaschizophrenia")
                        .execute(tasks, ui, storage));
        assertThrows(TimitomoException.class,
                () -> Parser.parse("deadline submit CS2103T /by April 20 1889 2237")
                        .execute(tasks, ui, storage));
        assertDoesNotThrow(() -> Parser.parse("deadline submit CS2103T /by 20-04-1889")
                .execute(tasks,ui,storage));
    }
}