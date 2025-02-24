package timitomo.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertDoesNotThrow(() -> Parser.parse("event CS2103T meeting /from 20-04-1889 0249 /to 30-04-1945")
                .execute(tasks, storage));
        assertThrows(TimitomoException.class, () ->
                Parser.parse("event CS2103T /from 20-04-1889 /to 31-04-1945").execute(tasks, storage));
        assertThrows(TimitomoException.class, () ->
                Parser.parse("event CS2103T /from 20-04-1889 /to 30-04-1945 2261").execute(tasks, storage));

        assertDoesNotThrow(() -> Parser.parse("event CS2103T meeting "
                + "/slot 10-03-2025 1000 /to 10-03-2025 1200 /slot 11-03-2025 1400 /to 11-03-2025 1600")
                .execute(tasks, storage));
        assertThrows(TimitomoException.class, () ->
                Parser.parse("event CS2103T /slot 15-04-2025 0900 /to").execute(tasks, storage));
        assertThrows(TimitomoException.class, () ->
                Parser.parse("event CS2103T /slot /to 20-06-2025 1800").execute(tasks, storage));
        assertEquals(2, tasks.size());
    }
}
