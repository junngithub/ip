package commands;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;
import exceptions.EmptyInputException;
import exceptions.InvalidInputException;

/**
 * Represents a SetCommand command that allows the user to set or update specific syntactical preferences
 * for task descriptions. The user can provide a keyword and its preferred replacement,
 * updating the system's syntax for parsing tasks.
 */
public class SetCommand extends Command {
    /**
     * Constructs a SetCommand with the provided user input string.
     *
     * @param userInput the input string provided by the user, which contains the keyword
     *     and the preferred replacement to be set in the system.
     */
    public SetCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes the set command, which updates the system's parsing syntax based on the user's input.
     * The command expects a valid user input with a keyword and its preferred replacement.
     * If the input is invalid or incomplete, the method throws an EmptyInputException.
     *
     * @param taskManager the TaskManager that manages tasks (not used in this command).
     * @param ui the UI to format the response with clear paragraph separation.
     * @param parser the Parser that processes the user input and updates the syntax accordingly.
     * @param store the Storage to manage task saving/loading (not used in this command).
     * @return a message indicating that the syntax has been updated and the list of syntax after the change.
     * @throws EmptyInputException if the user input is missing the preferred keyword.
     * @throws InvalidInputException if the preferred keyword is already being used.
     */
    @Override
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store)
            throws EmptyInputException, InvalidInputException {
        String[] arr = this.getUserInput().split(" ", 3);
        if (arr.length != 3) {
            throw new EmptyInputException("set", "description");
        }
        String keyword = arr[1];
        String preferredKeyword = arr[2];
        parser.updateSyntax(keyword, preferredKeyword);
        return "Here is your updated syntax:\n" + ui.showBorder() + parser.saySyntax();
    }
}
