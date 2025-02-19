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
import tasks.Task;

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
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setIcarus(icarus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(){
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
