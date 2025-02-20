package commands;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;

/**
 * Represents a InvalidCommand command that handles invalid or unrecognized user input.
 * This command simply returns the exception message as a response, indicating that
 * the command was not valid or recognised by the system.
 */
public class InvalidCommand extends Command {

    /**
     * Constructs an InvalidCommand with the provided exception message.
     *
     * @param exceptionMessage the input string provided by the user which is not a recognized command.
     */
    public InvalidCommand(String exceptionMessage) {
        super(exceptionMessage);
    }

    /**
     * Executes the invalid command, which simply returns the exception message as a response.
     * This is useful when the user enters an unrecognized or invalid command.
     *
     * @param taskManager the TaskManager that manages tasks (not used in this command).
     * @param ui the UI to format the response with clear paragraph separation (not used in this command).
     * @param parser the Parser to process the user input (not used in this command).
     * @param store the Storage to manage task saving/loading (not used in this command).
     * @return the exception message as the response.
     */
    @Override
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store) {
        return super.getUserInput();
    }
}
