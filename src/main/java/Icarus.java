import java.util.ArrayList;

import Commands.Command;
import Essentials.Parser;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;
import Tasks.Task;

public class Icarus {
    private UI ui;
    private Parser parser;
    private Storage store;
    private TaskManager taskManager;

    public Icarus()  {
        ArrayList<Task> list = new ArrayList<>();
        this.ui = new UI();
        this.parser = new Parser();
        this.taskManager = new TaskManager();
        this.store = new Storage();
        store.loadSave(taskManager);
        ui.greet();
        run();
    }

    private void run() {
        boolean isExiting = false;
        while (!isExiting) {
            try {
                String commandRead = ui.readCommand();
                Command command = parser.parseCommand(commandRead);
                if (command != null) {
                    command.execute(taskManager, ui, store);
                    isExiting = command.isExiting();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private void echo(String userInput) {
        System.out.println(userInput + "\n");
    }

    public static void main(String[] args) {
        Icarus chatbot = new Icarus();
    }
}
