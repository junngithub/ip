package commands;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;

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
     * @param taskManager the TaskManager that contains tasks to be read or manipulated based
     *     on the type of command
     * @param ui the UI to format the response with clear paragraph separation.
     * @param parser the Parser that processes the user input and creates the task.
     * @param store the Storage to manage task saving/loading (if needed by the command).
     * @return a string response.
     * @throws Exception if an error occurs during command execution.
     */
    public abstract String execute(TaskManager taskManager, UI ui, Parser parser, Storage store)
            throws Exception;

    public String getUserInput() {
        return userInput;
    }
}
