package timitomo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 * <br />
 * Taken from <a href="https://se-education.org/guides/tutorials/javaFx.html">here</a>.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Timitomo timitomo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image timitomoImage = new Image(this.getClass().getResourceAsStream("/images/Timitomo.png"));

    /**
     * Initializes the chatbot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.prefHeightProperty().bind(scrollPane.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getTimitomoDialog(
                        "Hello, I'm Timitomo." + System.lineSeparator() + "How can I help you?", timitomoImage)
        );
    }

    /** Injects the Timitomo instance */
    public void setTimitomo(Timitomo t) {
        timitomo = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Timitomo's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = timitomo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTimitomoDialog(response, timitomoImage)
        );
        userInput.clear();
    }
}
