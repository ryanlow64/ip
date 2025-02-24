package timitomo.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import timitomo.commands.ByeCommand;
import timitomo.commands.DeadlineCommand;
import timitomo.commands.DeleteCommand;
import timitomo.commands.EventCommand;
import timitomo.commands.FindCommand;
import timitomo.commands.ListCommand;
import timitomo.commands.MarkCommand;
import timitomo.commands.ToDoCommand;
import timitomo.commands.UnmarkCommand;
import timitomo.exceptions.TimitomoException;

class ParserTest {
    @Test
    void testParseByeCommand() throws TimitomoException {
        assertInstanceOf(ByeCommand.class, Parser.parse("bye"));
        assertThrows(TimitomoException.class, () -> Parser.parse("bye 37"));
    }

    @Test
    void testParseListCommand() throws TimitomoException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
        assertThrows(TimitomoException.class, () -> Parser.parse("list 37"));
    }

    @Test
    void testParseMarkCommand() throws TimitomoException {
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"));
        assertThrows(TimitomoException.class, () -> Parser.parse("mark"));
        assertThrows(TimitomoException.class, () -> Parser.parse("mark schwarzenegger"));
    }

    @Test
    void testParseUnmarkCommand() throws TimitomoException {
        assertInstanceOf(UnmarkCommand.class, Parser.parse("unmark 1"));
        assertThrows(TimitomoException.class, () -> Parser.parse("unmark"));
        assertThrows(TimitomoException.class, () -> Parser.parse("unmark schwarzenegger"));
    }

    @Test
    void testParseTodoCommand() throws TimitomoException {
        assertInstanceOf(ToDoCommand.class, Parser.parse("todo CS2103T iP"));
        assertThrows(TimitomoException.class, () -> Parser.parse("todo  "));
        assertInstanceOf(ToDoCommand.class, Parser.parse(
                "todo aquickbrownfoxjumpsoverthelazydogaquickbrownfoxjumpsoverthelazydogthebrownfoxisnowslow lalala"));
    }

    @Test
    void testParseDeadlineCommand() throws TimitomoException {
        assertInstanceOf(DeadlineCommand.class, Parser.parse("deadline submit CS2103T /by 20-04-1889"));
        assertInstanceOf(DeadlineCommand.class, Parser.parse("deadline submit CS2103T /by 20-04-1889 2237"));
        assertThrows(TimitomoException.class, () -> Parser.parse("deadline submit CS2103T"));
    }

    @Test
    void testParseEventCommand() throws TimitomoException {
        assertInstanceOf(EventCommand.class,
                Parser.parse("event CS2103T meeting /from 13-01-2025 /to 30-04-2025"));
        assertThrows(TimitomoException.class, () -> Parser.parse("event CS2103T meeting"));
        assertThrows(TimitomoException.class, () ->
                Parser.parse("event CS2103T meeting /to 30-04-2025 /from 13-01-2025"));
        assertThrows(TimitomoException.class, () ->
                Parser.parse("event CS2103T meeting /to 30-04-2025 /from 13-01-2025 lalala"));
        assertInstanceOf(EventCommand.class, Parser.parse("event "
                + "aquickbrownfoxjumpsoverthelazydogaquickbrownfoxjumpsoverthelazydogthebrownfoxisnowslow "
                + "/from 13-01-2025 /to 30-04-2025"));
        assertInstanceOf(EventCommand.class, Parser.parse("event "
                + "aquickbrownfoxjumpsoverthelazydogaquickbrownfoxjumpsoverthelazydogthebrownfoxisnowslow "
                + "/slot 13-01-2025 /to 30-04-2025 0115"
                + "/slot 14-01-2025 1537 /to 30-04-2025 /slot 11-01-2025 1111 /to 30-04-2025 2222"));
        assertThrows(TimitomoException.class, () -> Parser.parse("event CS2103T "
                + "/slot 13-01-2025 /to 30-04-2025 /to 14-01-2025 /slot 30-04-2025 /slot 11-01-2025 /to 30-04-2025"));
    }

    @Test
    void testParseDeleteCommand() throws TimitomoException {
        assertInstanceOf(DeleteCommand.class, Parser.parse("delete 2"));
        assertThrows(TimitomoException.class, () -> Parser.parse("delete  "));
        assertThrows(TimitomoException.class, () -> Parser.parse("delete schwarzenegger"));
    }

    @Test
    void testParseFindCommand() throws TimitomoException {
        assertThrows(TimitomoException.class, () -> Parser.parse("find  "));
        assertInstanceOf(FindCommand.class, Parser.parse("find schwarzenegger"));
    }

    @Test
    void testParseInvalidCommand() {
        assertThrows(TimitomoException.class, () -> Parser.parse(""));
        assertThrows(TimitomoException.class, () -> Parser.parse(
                "aquickbrownfoxjumpsoverthelazydogaquickbrownfoxjumpsoverthelazydogthebrownfoxisnowslow"));
    }
}
