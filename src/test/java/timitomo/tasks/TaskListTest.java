package timitomo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import timitomo.commands.ConfirmCommand;
import timitomo.exceptions.TimitomoException;
import timitomo.storage.StorageStub;

public class TaskListTest {
    private TaskList taskList;
    private Event event1;
    private Event event2;

    @BeforeEach
    void setUp() throws TimitomoException {
        taskList = new TaskList();
        event1 = new Event("CS2103T team meeting", false, "04-06-2025 0000", "04-06-2025 2004");
        event2 = new Event("Birth of an artist", false, "20-04-1889 1945", "20-04-1889 1945");
    }

    @Test
    void testAddDeleteTask() {
        taskList.addTask(event1);
        taskList.addTask(event2);
        assertEquals(2, taskList.size());
        assertEquals(event2, taskList.getTask(1));
        try {
            Task temp = taskList.deleteTask(0);
            assertEquals(event1, temp);
            assertEquals(1, taskList.size());
        } catch (TimitomoException e) {
            fail();
        }
    }

    @Test
    void testMarkUnmarkTask() {
        try {
            taskList.addTask(event1);
            taskList.addTask(event2);
            taskList.markTask(1);
            assertTrue(event2.isDone);
            assertFalse(event1.isDone);
            taskList.unmarkTask(1);
            assertFalse(event2.isDone);
            assertThrows(TimitomoException.class, () -> taskList.markTask(37));
        } catch (TimitomoException e) {
            fail();
        }
    }

    @Test
    void testDeleteTaskException() {
        TimitomoException e = assertThrows(TimitomoException.class, () -> taskList.deleteTask(0));
        assertEquals("Invalid task number!", e.getMessage());
    }

    @Test
    void testFindTask() {
        taskList.addTask(event1);
        taskList.addTask(event2);
        assertEquals(2, taskList.findTasks("t").size());
        assertTrue(taskList.findTasks("artist").contains(event2));
        assertTrue(taskList.findTasks("schwarzenegger").isEmpty());
    }

    @Test
    void testTentativeEvent() throws TimitomoException {
        taskList.addTask(event1);
        Event tentativeEvent = new Event("CS2103T meeting",
                new String[] {"20-04-1889 1000", "20-04-1889 1200"},
                new String[] {"30-04-1945 1000", "30-04-1945 1200"},
                new String[] {"12-03-2025 2020", "12-03-2025 2200"},
                new String[] {"29-04-2025 1700", "29-04-2025 1900"});
        taskList.addTask(tentativeEvent);
        taskList.addTask(event2);
        assertEquals(3, taskList.size());
        assertEquals(4, tentativeEvent.tentativeSlots.size());
        assertThrows(IllegalStateException.class, tentativeEvent::markAsDone);
        assertThrows(TimitomoException.class, () -> tentativeEvent.confirmSlot(-1));
        assertThrows(TimitomoException.class, () -> event2.confirmSlot(0));

        ConfirmCommand confirmCommand = new ConfirmCommand(1, 2);
        confirmCommand.execute(taskList, new StorageStub());
        assertEquals("12 Mar 2025 2020", tentativeEvent.start.format(Task.FORMAT_PRINT));
        assertEquals("12 Mar 2025 2200", tentativeEvent.end.format(Task.FORMAT_PRINT));
        assertEquals(0, tentativeEvent.tentativeSlots.size());
        assertThrows(TimitomoException.class, () -> tentativeEvent.confirmSlot(0));
    }
}
