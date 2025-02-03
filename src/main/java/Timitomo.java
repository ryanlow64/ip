import java.util.ArrayList;
import java.util.Scanner;

public class Timitomo {
    private Storage storage;
    private ArrayList<Task> tasks;

    public Timitomo() {
        tasks = new ArrayList<>();
    }

    private void greet() {
        print(String.format("Hello! I'm Timitomo.%nWhat can I do for you?"));
    }

    private void exit() {
        print("Goodbye!");
    }

    private void addTask(Task task) {
        tasks.add(task);
        print(String.format("I've added this task:%n    %s%nYou have %d %s in the list.",
                task.toString(), tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }

    private void deleteTask(int index) throws IllegalArgumentException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        Task task = tasks.remove(index);
        print(String.format("Alright, I've deleted this task:%n    %s%nYou have %d %s in the list.",
                task.toString(), tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }

    private void printTasks() throws TimitomoException {
        if (tasks.isEmpty()) {
            throw new TimitomoException("Nothing to do in task list. Stop slacking off!");
        }
        System.out.printf(">>> You have %d %s in the list. What are you waiting for?%n",
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf(">>> %d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println("----------------");
    }

    private void markTask(int index) throws IllegalArgumentException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        tasks.get(index).markAsDone();
        print(String.format("Nice! I've marked it as done:%n    %s", tasks.get(index).toString()));
    }

    private void unmarkTask(int index) throws IllegalArgumentException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        tasks.get(index).markAsNotDone();
        print(String.format("I've marked it as not done yet. Get to work!%n    %s", tasks.get(index).toString()));
    }

    private void handleIO() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                String[] input = line.split(" ", 2);
                ActionType action = ActionType.getAction(input[0]);
                switch (action) {
                case BYE:
                    if (input.length > 1) {
                        throw new TimitomoException("You don't need anything after \"bye\". " +
                                "Enter \"bye\" when you want to leave!");
                    }
                    exit();
                    scanner.close();
                    return;
                case LIST:
                    try {
                        if (input.length > 1) {
                            throw new TimitomoException("You don't need anything after \"list\". " +
                                    "Enter \"list\" to see all your tasks!");
                        }
                        printTasks();
                    } catch (TimitomoException e) {
                        print(e.getMessage());
                    }
                    break;
                case MARK:
                    try {
                        int index = Integer.parseInt(input[1]) - 1;
                        markTask(index);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        print("Specify task number to mark as done.");
                    }
                    break;
                case UNMARK:
                    try {
                        int index = Integer.parseInt(input[1]) - 1;
                        unmarkTask(index);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        print("Specify task number to mark as not done.");
                    }
                    break;
                case TODO:
                    try {
                        String description = input[1];
                        addTask(new ToDo(description, false));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        print("Missing description. What do you want to do?");
                    }
                    break;
                case DEADLINE:
                    try {
                        String[] args = input[1].split(" /by ", 2);
                        String description = args[0].trim();
                        String by = args[1].trim();
                        if (description.isEmpty() || by.isEmpty()) {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        addTask(new Deadline(description, false, by));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        print("I didn't quite catch that. Use: \"deadline <description> /by <due date>\".");
                    }
                    break;
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
                        addTask(new Event(description, false, start, end));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        print("I didn't quite catch that. Use: \"event <description> /from <start> /to <end>\".");
                    }
                    break;
                case DELETE:
                    try {
                        int index = Integer.parseInt(input[1]) - 1;
                        deleteTask(index);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        print("Specify task number to delete.");
                    }
                    break;
                default:
                    throw new TimitomoException("I don't know what that means.");
                }
            } catch (IllegalArgumentException | TimitomoException e) {
                print(e.getMessage());
            }
        }
    }

    private void print(String text) {
        for (String line : text.split(System.lineSeparator())) {
            System.out.println(">>> " + line);
        }
        System.out.println("----------------");
    }

    public static void main(String[] args) {
        Timitomo bot = new Timitomo();
        bot.greet();
        bot.handleIO();
    }
}
