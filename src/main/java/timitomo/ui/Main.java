package timitomo.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 * <br />
 * Taken from <a href="https://se-education.org/guides/tutorials/javaFx.html">here</a>.
 */
public class Main extends Application {
    private Timitomo timitomo = new Timitomo("./data/timitomo.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Timitomo");
            fxmlLoader.<MainWindow>getController().setTimitomo(timitomo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
