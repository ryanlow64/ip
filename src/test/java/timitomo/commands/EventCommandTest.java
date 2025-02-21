package timitomo.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import timitomo.exceptions.TimitomoException;
import timitomo.parser.Parser;
import timitomo.storage.Storage;
import timitomo.storage.StorageStub;
import timitomo.tasks.TaskList;

class EventCommandTest {
    @Test
    void executeTest() {
        TaskList tasks = new TaskList();
        Storage storage = new StorageStub();
        assertThrows(TimitomoException.class, () ->
                Parser.parse("event CS2103T meeting /from 13-01-2025 /to 30-04-2025 lalalalalaschizophrenia")
                        .execute(tasks, storage));
        assertThrows(TimitomoException.class, () ->
                Parser.parse("event CS2103T meeting /from April 20 1889 /to April 31 1945")
                        .execute(tasks, storage));
        assertDoesNotThrow(() -> Parser.parse("event CS2103T meeting /from 20-04-1889 /to 31-04-1945")
                .execute(tasks, storage));
    }
}
