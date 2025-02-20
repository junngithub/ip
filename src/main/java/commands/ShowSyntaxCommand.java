package commands;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;

/**
 * Represents a command that displays the syntax and format instructions to the user.
 * This command also provides reminders on how to correctly format task inputs for the system.
 */
public class ShowSyntaxCommand extends Command {

    /**
     * Constructs a ShowSyntaxCommand.
     * This constructor does not require any input.
     */
    public ShowSyntaxCommand() {
        super();
    }

    /**
     * Executes the command by displaying the current syntax and format instructions.
     * The user will be shown the current syntax along with examples of valid task formats.
     *
     *
     * @param taskManager the TaskManager that manages tasks (not used in this command).
     * @param ui the UI to format the response with clear paragraph separation.
     * @param parser the Parser that processes the user input (used here to retrieve current syntax).
     * @param store the Storage for saving or loading task data (not used in this method).
     * @return a string message containing the current syntax list and example task formats.
     */
    @Override
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store) {
        String response = "Here's your syntax: \n" + ui.showBorder() + parser.saySyntax() + ui.showBorder();
        response += "Reminder to please adhere to the following formats: \n";
        response += """
                todo ...
                deadline ... /by ...
                event ... /from ... /to
                """;
        return response;
    }
}
