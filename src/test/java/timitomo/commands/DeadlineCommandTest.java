package timitomo.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import timitomo.exceptions.TimitomoException;
import timitomo.parser.Parser;
import timitomo.storage.Storage;
import timitomo.storage.StorageStub;
import timitomo.tasks.TaskList;

class DeadlineCommandTest {
    @Test
    void executeTest() {
        TaskList tasks = new TaskList();
        Storage storage = new StorageStub();
        assertThrows(TimitomoException.class, () ->
                Parser.parse("deadline submit CS2103T /by 20-04-1889 2237 lalalalalaschizophrenia")
                        .execute(tasks, storage));
        assertThrows(TimitomoException.class, () ->
                Parser.parse("deadline submit CS2103T /by April 20 1889 2237")
                        .execute(tasks, storage));
        assertDoesNotThrow(() -> Parser.parse("deadline submit CS2103T /by 20-04-1889")
                .execute(tasks, storage));
        assertThrows(TimitomoException.class, () ->
                Parser.parse("deadline submit CS2103T /by 29-02-2025 2237")
                        .execute(tasks, storage));
    }
}
