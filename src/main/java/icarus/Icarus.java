package icarus;

import commands.Command;
import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;
import javafx.util.Pair;


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
     * Processes the user input by parsing the command, executing it, and
     * returning the appropriate response.
     * The response includes the result of the command execution along with borders.
     *
     * @param userInput the input provided by the user, which will be parsed into a command.
     * @return the response generated from executing the parsed command, including any exceptions.
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
     * Prepares the necessary data for saving when exiting the program.
     * Returns a pair of pairs containing the storage and parser objects, along with the task manager.
     *
     * @return a pair of pairs containing the storage and parser objects, and the task manager.
     */
    public Pair<Pair<Storage, Parser>, TaskManager> prepareExit() {
        return new Pair<>(new Pair<>(this.store, this.parser), taskManager);
    }

}

