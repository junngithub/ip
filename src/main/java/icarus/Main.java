package icarus;

import java.io.IOException;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * The main entry point for the Icarus application.
 * This class extends `Application` and sets up the user interface for the chatbot,
 * initializing necessary components and handling the startup and shutdown process.
 */
public class Main extends Application {

    private Icarus icarus = new Icarus();

    /**
     * Initializes the main window and sets up the scene for the Icarus application.
     * It loads the FXML file for the main window, sets the scene and stage properties,
     * and links the `Icarus` instance to the controller.
     *
     * @param stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Icarus");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setIcarus(icarus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the shutdown process of the application.
     * It prepares the necessary data, including the task list and user preferences,
     * and saves them to storage before the application exits.
     */
    @Override
    public void stop() {
        Pair<Pair<Storage, Parser>, TaskManager> pairs = icarus.prepareExit();
        Storage store = pairs.getKey().getKey();
        Parser parser = pairs.getKey().getValue();
        TaskManager taskManager = pairs.getValue();
        try {
            store.updateSyntaxPreferences(parser);
            store.updateTasks(taskManager.getList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
