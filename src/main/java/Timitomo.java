import java.util.Scanner;

public class Timitomo {
    public Timitomo() {}

    private void greet() {
        print("Hello! I'm Timitomo." + System.lineSeparator() + "What can I do for you?");
    }

    private void exit() {
        print("Goodbye!");
    }

    private void handleIO() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            }
            print(input);
        }
        scanner.close();
    }

    private void print(String text) {
        for (String line : text.split(System.lineSeparator())) {
            System.out.println(">> " + line);
        }
        System.out.println("----------------");
    }

    public static void main(String[] args) {
        Timitomo bot = new Timitomo();
        bot.greet();
        bot.handleIO();
    }
}
