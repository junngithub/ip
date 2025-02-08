package Icarus;

import Commands.Command;
import Essentials.Parser;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;


/**
 * Represents the main program for the Icarus.Icarus chatbot.
 * This class manages the interaction between the user and the task manager,
 * handles command parsing and execution, and provides an interface for input and output.
 */
public class Icarus {
    private UI ui;
    private Parser parser;
    private Storage store;
    private TaskManager taskManager;

    /**
     * Constructs an Icarus.Icarus chatbot instance.
     * Initializes the user interface, parser, task manager, and storage system,
     * then loads previously saved data and greets the user.
     */
    public Icarus()  {
        this.ui = new UI();
        this.parser = new Parser();
        this.taskManager = new TaskManager();
        this.store = new Storage();
        store.loadSave(taskManager, parser);
        ui.greet();
        run();
    }

    /**
     * Main loop of the chatbot. Continuously reads commands from the user,
     * parses and executes them, and exits when the user provides an exit command.
     */
    private void run() {
        boolean isExiting = false;
        while (!isExiting) {
            try {
                String commandRead = ui.readCommand();
                ui.showBorder();
                Command command = parser.parseCommand(commandRead);
                if (command != null) {
                    command.execute(taskManager, ui, parser, store);
                    isExiting = command.isExiting();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                ui.showBorder();
            }
        }
    }

    /**
     * Echoes the user input to the console with a newline after it.
     *
     * @param userInput the input to be echoed.
     */
    private void echo(String userInput) {
        System.out.println(userInput + "\n");
    }

    /**
     * Main method to run the Icarus.Icarus chatbot.
     *
     * @param args command line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        Icarus chatbot = new Icarus();
    }
}
