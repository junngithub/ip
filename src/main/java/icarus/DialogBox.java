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
 * TODO
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private BorderPane displayPicture;

    /**
     * TODO
     * @param text
     * @param img
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

    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getIcarusDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

}
