public class Timitomo {
    public Timitomo() {}

    private void greet() {
        System.out.println("Hello! I'm Timitomo.\nWhat can I do for you?\n");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Timitomo bot = new Timitomo();
        bot.greet();
        bot.exit();
    }
}
