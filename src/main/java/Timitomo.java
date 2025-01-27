import java.util.Scanner;

public class Timitomo {
    private String[] tasks;
    private int taskCount;
    private static final int MAX_TASKS = 100;

    public Timitomo() {
        tasks = new String[MAX_TASKS];
        taskCount = 0;
    }

    private void greet() {
        print("Hello! I'm Timitomo." + System.lineSeparator() + "What can I do for you?");
    }

    private void exit() {
        print("Goodbye!");
    }

    private void addTask(String task) {
        if (taskCount >= MAX_TASKS) {
            print("Task overflow error! Focus on the current tasks first!");
            return;
        }
        tasks[taskCount] = task;
        taskCount++;
        print("added: " + task);
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

    private void handleIO() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
            case "":
                break;
            case "bye":
                exit();
                scanner.close();
                return;
            case "list":
                printTasks();
                break;
            default:
                addTask(input);
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
