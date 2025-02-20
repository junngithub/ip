package icarus;

import java.util.Timer;
import java.util.TimerTask;

import essentials.UI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the main window of the Icarus chatbot application.
 * This class is responsible for managing the user interface,
 * handling user input, and displaying the conversation between the user and Icarus.
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

    private Icarus icarus;
    private UI ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image icarusImage = new Image(this.getClass().getResourceAsStream("/images/icarus.jpg"));

    /**
     * Initializes the main window.
     * Binds the scroll pane's vertical value property to the dialog container's height
     * so that the window automatically scrolls to show the most recent messages.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Icarus instance */
    public void setIcarus(Icarus i) {
        icarus = i;
        ui = new UI();
        dialogContainer.getChildren().addAll(DialogBox.getIcarusDialog(ui.greet(), icarusImage));
    }

    /**
     * Handles user input, creates two dialog boxes (one for the user input and one for Icarus' response),
     * and appends them to the dialog container. Clears the user input field after processing.
     * Exits the program if the user types "bye".
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = icarus.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getIcarusDialog(response, icarusImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            exit();
        }
    }

    /**
     * Initiates the exit process of the application by scheduling a delayed task to exit the platform.
     * The platform exits after a 1-second delay.
     */
    private void exit() {
        new Timer(true).schedule(new TimerTask() {
            public void run () {
                Platform.exit();
            }
        }, 1000);
    }
}
