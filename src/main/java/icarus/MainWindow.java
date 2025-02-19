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
 * TODO
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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
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

    private void exit() {
        new Timer(true).schedule(new TimerTask() {
            public void run () {
                Platform.exit();
            }
        }, 1000);
    }
}
