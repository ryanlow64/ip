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

    private void addTask(Task task) {
        if (taskCount >= MAX_TASKS) {
            print("Task overflow error! Focus on the current tasks first!");
            return;
        }
        tasks[taskCount] = task;
        taskCount++;
        print(String.format("I've added this task:%n  %s%nYou have %d %s in the list.",
                task.toString(), taskCount, taskCount == 1 ? "task" : "tasks"));
    }

    private void printTasks() {
        if (taskCount == 0) {
            print("Nothing to do in task list. Stop slacking off!");
            return;
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
            switch (input[0]) {
            case "":
                break;
            case "bye":
                exit();
                scanner.close();
                return;
            case "list":
                printTasks();
                break;
            case "mark":
                try {
                    int index = Integer.parseInt(input[1]) - 1;
                    markTask(index);
                } catch (ArrayIndexOutOfBoundsException e) {
                    print("Specify task number to mark.");
                } catch (IllegalArgumentException e) {
                    print(e.getMessage());
                }
                break;
            case "unmark":
                try {
                    int index = Integer.parseInt(input[1]) - 1;
                    unmarkTask(index);
                } catch (ArrayIndexOutOfBoundsException e) {
                    print("Specify task number to unmark.");
                } catch (IllegalArgumentException e) {
                    print(e.getMessage());
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
                    addTask(new Deadline(args[0], args[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    print("I didn't quite catch that. What deadline do you have?");
                }
                break;
            case "event":
                try {
                    String[] args = input[1].split(" /from ", 2);
                    String[] times = args[1].split(" /to ", 2);
                    addTask(new Event(args[0], times[0], times[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    print("I didn't quite catch that. What event do you have?");
                }
                break;
            default:
                print("I don't know what that means.");
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
