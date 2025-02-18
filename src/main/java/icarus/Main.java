package icarus;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * TODO
 */
public class Main extends Application {

    private Icarus icarus = new Icarus();

    /**
     * TODO
     * @param stage TODO
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setIcarus(icarus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
