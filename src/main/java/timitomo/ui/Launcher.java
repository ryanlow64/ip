package timitomo.ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * <br />
 * Taken from <a href="https://se-education.org/guides/tutorials/javaFx.html">here</a>.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
