package commands;

import java.io.IOException;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;

/**
 * Represents an ExitCommand command that handles the exit action of the program.
 * Inherits from Command and provides functionality to save the task list to storage,
 * print a farewell message, and indicate that the program should exit.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     */
    public ExitCommand() {}

    /**
     * Executes the command by saving the current task list to storage
     * and printing a farewell message
     *
     * @param taskManager the TaskManager to retrieve the task list for saving.
     * @param ui the UI to format the response with clear paragraph separation (not used in this method).
     * @param parser the Parser to process the user input (not used in this method).
     * @param store the Storage for saving data in the task list to a specified file.
     * @return a string response that bids farewell to the user.
     * @throws IOException if an error occurs while saving the task list to the file.
     */
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store)
            throws IOException {
        store.updateTasks(taskManager.getList());
        store.updateSyntaxPreferences(parser);
        return "Bye! See you next time, my friend.\n";
    }
}
