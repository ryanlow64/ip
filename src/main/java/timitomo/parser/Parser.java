package timitomo.parser;

import timitomo.commands.ByeCommand;
import timitomo.commands.Command;
import timitomo.commands.DeadlineCommand;
import timitomo.commands.DeleteCommand;
import timitomo.commands.EventCommand;
import timitomo.commands.ListCommand;
import timitomo.commands.MarkCommand;
import timitomo.commands.ToDoCommand;
import timitomo.commands.UnmarkCommand;
import timitomo.enums.CommandType;
import timitomo.exceptions.TimitomoException;

public class Parser {
    public static Command parse(String line) throws TimitomoException {
        String[] input = line.split(" ", 2);
        CommandType action = CommandType.getAction(input[0]);
        switch (action) {
        case BYE:
            if (input.length > 1) {
                throw new TimitomoException("You don't need anything after \"bye\". " +
                        "Enter \"bye\" when you want to leave!");
            }
            return new ByeCommand();
        case LIST:
            if (input.length > 1) {
                throw new TimitomoException("You don't need anything after \"list\". " +
                        "Enter \"list\" to see all your tasks!");
            }
            return new ListCommand();
        case MARK:
            try {
                int index = Integer.parseInt(input[1]) - 1;
                return new MarkCommand(index);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new TimitomoException("Specify task number to mark as done.");
            }
        case UNMARK:
            try {
                int index = Integer.parseInt(input[1]) - 1;
                return new UnmarkCommand(index);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new TimitomoException("Specify task number to mark as not done.");
            }
        case TODO:
            try {
                String description = input[1].trim();
                return new ToDoCommand(description);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new TimitomoException("Missing description. What do you want to do?");
            }
        case DEADLINE:
            try {
                String[] args = input[1].split(" /by ", 2);
                String description = args[0].trim();
                String by = args[1].trim();
                if (description.isEmpty() || by.isEmpty()) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                return new DeadlineCommand(description, by);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new TimitomoException(
                        "Missing details. Use: \"deadline <description> /by <due date>\".");
            }
        case EVENT:
            try {
                String[] args = input[1].split(" /from ", 2);
                String[] times = args[1].split(" /to ", 2);
                String description = args[0].trim();
                String start = times[0].trim();
                String end = times[1].trim();
                if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                return new EventCommand(description, start, end);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new TimitomoException(
                        "Missing details. Use: \"event <description> /from <start> /to <end>\".");
            }
        case DELETE:
            try {
                int index = Integer.parseInt(input[1]) - 1;
                return new DeleteCommand(index);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new TimitomoException("Specify task number to delete.");
            }
        default:
            throw new TimitomoException("I don't know what that means.");
        }
    }
}
