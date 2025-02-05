package timitomo.parser;

import org.junit.jupiter.api.Test;
import timitomo.commands.ByeCommand;
import timitomo.commands.DeadlineCommand;
import timitomo.commands.DeleteCommand;
import timitomo.commands.EventCommand;
import timitomo.commands.ListCommand;
import timitomo.commands.MarkCommand;
import timitomo.commands.ToDoCommand;
import timitomo.commands.UnmarkCommand;
import timitomo.exceptions.TimitomoException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    @Test
    void parseByeCommandTest() throws TimitomoException {
        assertInstanceOf(ByeCommand.class, Parser.parse("bye"));
        assertThrows(TimitomoException.class, () -> Parser.parse("bye 37"));
    }

    @Test
    void parseListCommandTest() throws TimitomoException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
        assertThrows(TimitomoException.class, () -> Parser.parse("list 37"));
    }

    @Test
    void parseMarkCommandTest() throws TimitomoException {
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"));
        assertThrows(TimitomoException.class, () -> Parser.parse("mark"));
        assertThrows(TimitomoException.class, () -> Parser.parse("mark schwarzenegger"));
    }

    @Test
    void parseUnmarkCommandTest() throws TimitomoException {
        assertInstanceOf(UnmarkCommand.class, Parser.parse("unmark 1"));
        assertThrows(TimitomoException.class, () -> Parser.parse("unmark"));
        assertThrows(TimitomoException.class, () -> Parser.parse("unmark schwarzenegger"));
    }

    @Test
    void parseTodoCommandTest() throws TimitomoException {
        assertInstanceOf(ToDoCommand.class, Parser.parse("todo CS2103T iP"));
        assertThrows(TimitomoException.class, () -> Parser.parse("todo"));
        assertInstanceOf(ToDoCommand.class, Parser.parse(
                "todo aquickbrownfoxjumpsoverthelazydogaquickbrownfoxjumpsoverthelazydogthebrownfoxisnowslow lalala"));
    }

    @Test
    void parseDeadlineCommandTest() throws TimitomoException {
        assertInstanceOf(DeadlineCommand.class, Parser.parse("deadline submit CS2103T /by 20-04-1889"));
        assertInstanceOf(DeadlineCommand.class, Parser.parse("deadline submit CS2103T /by 20-04-1889 2237"));
        assertThrows(TimitomoException.class, () -> Parser.parse("deadline submit CS2103T"));
    }

    @Test
    void parseEventCommandTest() throws TimitomoException {
        assertInstanceOf(EventCommand.class, Parser.parse("event CS2103T meeting /from 13-01-2025 /to 30-04-2025"));
        assertThrows(TimitomoException.class, () -> Parser.parse("event CS2103T meeting"));
        assertThrows(TimitomoException.class, () -> Parser.parse("event CS2103T meeting /to 30-04-2025 /from 13-01-2025"));
        assertThrows(TimitomoException.class, () -> Parser.parse("event CS2103T meeting /to 30-04-2025 /from 13-01-2025 lalala"));
        assertInstanceOf(EventCommand.class, Parser.parse("event " +
                "aquickbrownfoxjumpsoverthelazydogaquickbrownfoxjumpsoverthelazydogthebrownfoxisnowslow /from 13-01-2025 /to 30-04-2025"));
    }

    @Test
    void parseDeleteCommandTest() throws TimitomoException {
        assertInstanceOf(DeleteCommand.class, Parser.parse("delete 2"));
        assertThrows(TimitomoException.class, () -> Parser.parse("delete"));
        assertThrows(TimitomoException.class, () -> Parser.parse("delete schwarzenegger"));
    }

    @Test
    void parseInvalidCommandTest() {
        assertThrows(TimitomoException.class, () -> Parser.parse(""));
        assertThrows(TimitomoException.class, () -> Parser.parse("aquickbrownfoxjumpsoverthelazydogaquickbrownfoxjumpsoverthelazydogthebrownfoxisnowslow"));
    }
}