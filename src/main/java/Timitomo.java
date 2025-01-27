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

    private void addTask(String description) {
        if (taskCount >= MAX_TASKS) {
            print("Task overflow error! Focus on the current tasks first!");
            return;
        }
        tasks[taskCount] = new Task(description);
        taskCount++;
        print("added: " + description);
    }

    private void printTasks() {
        if (taskCount == 0) {
            print("Nothing to do in task list. Stop slacking off!");
            return;
        }
        for (int i = 0; i < taskCount; i++) {
            print(String.format("%d. %s", i+1, tasks[i]), i == taskCount - 1);
        }
    }

    private void markTask(int index) {
        if (index < 0 || index >= taskCount) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        tasks[index].markAsDone();
        print(String.format("Nice! I've marked it as done.%n    %s", tasks[index].toString()));
    }

    private void unmarkTask(int index) {
        if (index < 0 || index >= taskCount) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        tasks[index].markAsNotDone();
        print(String.format("I've marked it as not done yet. Get to work!%n    %s", tasks[index].toString()));
    }

    private void handleIO() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String[] input = scanner.nextLine().trim().split(" ");
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
            default:
                String description = String.join(" ", input);
                addTask(description);
            }
        }
    }

    private void print(String text) {
        for (String line : text.split(System.lineSeparator())) {
            System.out.println(">>> " + line);
        }
        System.out.println("----------------");
    }

    private void print(String text, boolean flag) {
        for (String line : text.split(System.lineSeparator())) {
            System.out.println(">>> " + line);
        }
        if (flag) {
            System.out.println("----------------");
        }
    }

    public static void main(String[] args) {
        Timitomo bot = new Timitomo();
        bot.greet();
        bot.handleIO();
    }
}
