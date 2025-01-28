import java.util.Scanner;

public class Timitomo {
    private Task[] tasks;
    private int taskCount;
    private static final int MAX_TASKS = 100;

    public Timitomo() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    private void greet() {
        print(String.format("Hello! I'm Timitomo.%nWhat can I do for you?"));
    }

    private void exit() {
        print("Goodbye!");
    }

    private void addTask(Task task) throws TimitomoException {
        if (taskCount >= MAX_TASKS) {
            throw new TimitomoException("Task overflow error! Focus on the current tasks first!");
        }
        tasks[taskCount] = task;
        taskCount++;
        print(String.format("I've added this task:%n  %s%nYou have %d %s in the list.",
                task.toString(), taskCount, taskCount == 1 ? "task" : "tasks"));
    }

    private void printTasks() throws TimitomoException {
        if (taskCount == 0) {
            throw new TimitomoException("Nothing to do in task list. Stop slacking off!");
        }
        print(String.format("You have %d %s in the list. What are you waiting for?%n",
                taskCount, taskCount == 1 ? "task" : "tasks"));
        for (int i = 0; i < taskCount; i++) {
            System.out.printf(">>> %d. %s%n", i+1, tasks[i]);
        }
        System.out.println("----------------");
    }

    private void markTask(int index) throws IllegalArgumentException {
        if (index < 0 || index >= taskCount) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        tasks[index].markAsDone();
        print(String.format("Nice! I've marked it as done:%n  %s", tasks[index].toString()));
    }

    private void unmarkTask(int index) throws IllegalArgumentException {
        if (index < 0 || index >= taskCount) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        tasks[index].markAsNotDone();
        print(String.format("I've marked it as not done yet. Get to work!%n  %s", tasks[index].toString()));
    }

    private void handleIO() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine().trim();
            String[] input = line.split(" ", 2);
            try {
                switch (input[0]) {
                case "":
                    break;
                case "bye":
                    if (input.length > 1) {
                        throw new TimitomoException("You don't need anything after \"bye\". " +
                                "Enter \"bye\" when you want to leave!");
                    }
                    exit();
                    scanner.close();
                    return;
                case "list":
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
                case "mark":
                    try {
                        int index = Integer.parseInt(input[1]) - 1;
                        markTask(index);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        print("Specify task number to mark as done.");
                    }
                    break;
                case "unmark":
                    try {
                        int index = Integer.parseInt(input[1]) - 1;
                        unmarkTask(index);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        print("Specify task number to mark as not done.");
                    }
                    break;
                case "todo":
                    try {
                        String description = input[1];
                        addTask(new ToDo(description));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        print("Missing description. What do you want to do?");
                    }
                    break;
                case "deadline":
                    try {
                        String[] args = input[1].split(" /by ", 2);
                        String description = args[0].trim();
                        String by = args[1].trim();
                        if (description.isEmpty() || by.isEmpty()) {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        addTask(new Deadline(description, by));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        print("I didn't quite catch that. Use: \"deadline <description> /by <due date>\".");
                    }
                    break;
                case "event":
                    try {
                        String[] args = input[1].split(" /from ", 2);
                        String[] times = args[1].split(" /to ", 2);
                        String description = args[0].trim();
                        String start = times[0].trim();
                        String end = times[1].trim();
                        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        addTask(new Event(description, start, end));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        print("I didn't quite catch that. Use: \"event <description> /from <start> /to <end>\".");
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
