package Commands;

import Essentials.Parser;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;

/**
 * Represents a generic command with a given user input.
 * Subclasses of Command will implement the execute method
 * to define the action for each specific command.
 */
public abstract class Command {
    private String userInput;

    /**
     * Constructs a Command object with no specified user input
     */
    public Command() {
    }

    /**
     * Constructs a Command object with the specified user input
     * @param userInput the input provided by the user
     */
    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command. This method must be implemented by subclasses to define the specific
     * behavior of the command.
     *
     * @param taskManager the TaskManager to manage tasks for the command..
     * @param ui the UI to interact with the user.
     * @param parser the Parser that processes the user input and creates the task.
     * @param store the {@link Storage} to manage task saving/loading (if needed by the command).
     * @throws Exception if an error occurs during command execution.
     */
    public abstract String execute (TaskManager taskManager, UI ui, Parser parser, Storage store)
            throws Exception;

    /**
     * Determines whether the command indicates that the program should exit.
     * Default implementation returns false, meaning the program does not exit.
     * Subclasses may override this method to return true if the command requires an exit.
     *
     * @return true if the command ends the program, otherwise false.
     */
    public boolean isExiting() {
        return false;
    }
    public String getUserInput() {
        return userInput;
    }
}
