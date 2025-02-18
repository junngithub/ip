package icarus;

import commands.Command;
import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;


/**
 * Represents the main program for the Icarus chatbot.
 * This class manages the interaction between the user and the task manager,
 * handles command parsing and execution, and provides an interface for input and output.
 */
public class Icarus {
    private UI ui;
    private Parser parser;
    private Storage store;
    private TaskManager taskManager;

    /**
     * Constructs an Icarus chatbot instance.
     * Initializes the user interface, parser, task manager, and storage system,
     * then loads previously saved data and greets the user.
     */
    public Icarus() {
        this.ui = new UI();
        this.parser = new Parser();
        this.taskManager = new TaskManager();
        this.store = new Storage();
        store.loadSyntaxPreferences(parser);
        store.loadSavedTasks(taskManager, parser);
    }


    /**
     * TODO
     */
    public String getResponse(String userInput) {
        StringBuilder response = new StringBuilder();
        try {
            Command command = parser.parseCommand(userInput);
            assert command != null;
            response.append(ui.showBorder());
            response.append(command.execute(taskManager, ui, parser, store));
        } catch (Exception e) {
            response.append(e.toString());
        } finally {
            response.append(ui.showBorder());
        }
        return response.toString();
    }



    /**
     * Echoes the user input to the console with a newline after it.
     *
     * @param userInput the input to be echoed.
     */
    private void echo(String userInput) {
        System.out.println(userInput + "\n");
    }
}

