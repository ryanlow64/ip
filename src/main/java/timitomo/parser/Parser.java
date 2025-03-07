package timitomo.parser;

import timitomo.commands.ByeCommand;
import timitomo.commands.Command;
import timitomo.commands.ConfirmCommand;
import timitomo.commands.DeadlineCommand;
import timitomo.commands.DeleteCommand;
import timitomo.commands.EventCommand;
import timitomo.commands.FindCommand;
import timitomo.commands.ListCommand;
import timitomo.commands.MarkCommand;
import timitomo.commands.ToDoCommand;
import timitomo.commands.UnmarkCommand;
import timitomo.enums.CommandType;
import timitomo.exceptions.TimitomoException;

/**
 * Interprets user input and
 * generates the corresponding {@code Command} objects.
 */
public class Parser {
    /**
     * Parses the user input string and builds the corresponding {@code Command} object.
     *
     * @param line The user input string.
     * @return The {@code Command} object corresponding to the user input.
     * @throws TimitomoException If the input format is invalid.
     */
    public static Command parse(String line) throws TimitomoException {
        String[] input = line.replaceAll("\\s+", " ").strip().split(" ", 2);
        CommandType action = CommandType.getAction(input[0]);
        switch (action) {
        case BYE:
            if (input.length > 1) {
                throw new TimitomoException("You don't need anything after \"bye\". "
                        + "Enter \"bye\" when you want to leave!");
            }
            return new ByeCommand();
        case CONFIRM:
            try {
                String[] args = input[1].split(" ", 2);
                int taskIndex = Integer.parseInt(args[0].trim()) - 1;
                int slotIndex = Integer.parseInt(args[1].trim()) - 1;
                return new ConfirmCommand(taskIndex, slotIndex);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new TimitomoException("Missing details. Use: \"confirm <task number> <slot number>\"");
            }
        case LIST:
            if (input.length > 1) {
                throw new TimitomoException("You don't need anything after \"list\". "
                        + "Enter \"list\" to see all your tasks!");
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
                if (description.isEmpty()) {
                    throw new ArrayIndexOutOfBoundsException();
                }
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
                if (input[1].contains("/slot")) {
                    String[] args = input[1].split(" /slot ");
                    String description = args[0].trim();
                    if (args.length < 3 || description.isEmpty()) {
                        throw new TimitomoException("Missing details. Use: \"event <description> "
                                + "/slot <start> /to <end> /slot <start> /to <end> ...\"");
                    }
                    String[][] slots = new String[args.length - 1][2];
                    for (int i = 1; i < args.length; i++) {
                        String[] times = args[i].split(" /to ", 2);
                        if (times.length != 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                            throw new TimitomoException("Missing details. Use: \"event <description> "
                                    + "/slot <start> /to <end> /slot <start> /to <end> ...\"");
                        }
                        slots[i - 1] = times;
                    }
                    return new EventCommand(description, slots);
                } else {
                    String[] args = input[1].split(" /from ", 2);
                    String[] times = args[1].split(" /to ", 2);
                    String description = args[0].trim();
                    if (description.isEmpty() || times.length != 2
                            || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                        throw new TimitomoException(
                                "Missing details. Use: \"event <description> /from <start> /to <end>\".");
                    }
                    return new EventCommand(description, times);
                }
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
        case FIND:
            try {
                String keyword = input[1].trim();
                if (keyword.isEmpty()) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                return new FindCommand(keyword);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new TimitomoException("Missing keyword. What do you want to find?");
            }
        default:
            throw new TimitomoException("I don't know what that means...");
        }
    }
}
