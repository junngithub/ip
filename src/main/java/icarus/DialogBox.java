package icarus;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * The DialogBox class represents a dialog box that displays a message and an image (profile picture).
 * It is used to create a visual representation of user input and program responses in a conversation-like interface.
 * The dialog box can be flipped to change the alignment of the text and image.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private BorderPane displayPicture;

    /**
     * Constructs a new DialogBox with the given text and image.
     * The dialog box is initialized with the provided text and an image that is displayed in a circular shape.
     *
     * @param text the text to be displayed in the dialog box.
     * @param img the image to be displayed as the profile picture inside the dialog box.
     */
    private DialogBox(String text, Image img) {
        this.displayPicture = new BorderPane();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Circle cir2 = new Circle(50, 50, 40);
        cir2.setFill(new ImagePattern(img));
        dialog.setText(text);
        displayPicture.setCenter(cir2);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates and returns a DialogBox for user input with the specified text and image.
     * The user's dialog will have the image on the right side.
     *
     * @param s the text to be displayed for the user.
     * @param i the image to be displayed as the user's profile picture.
     * @return a DialogBox displaying the user's input.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates and returns a DialogBox for Icarus (the program's responses) with the specified text and image.
     * Icarus's dialog will have the image on the left side.
     *
     * @param text the text to be displayed for Icarus's response.
     * @param img the image to be displayed as Icarus's profile picture.
     * @return a DialogBox displaying Icarus's response.
     */
    public static DialogBox getIcarusDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

}
