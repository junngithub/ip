package commands;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;
import exceptions.EmptyInputException;
import exceptions.InvalidInputException;

/**
 * TODO
 */
public class SetCommand extends Command {
    public SetCommand(String userInput) {
        super(userInput);
    }

    /**
     * TODO
     * @param taskManager the TaskManager to manage tasks for the command..
     * @param ui the UI to interact with the user.
     * @param parser the Parser that processes the user input and creates the task.
     * @param store the {@link Storage} to manage task saving/loading (if needed by the command).
     * @return TODO
     * @throws EmptyInputException TODO
     * @throws InvalidInputException TODO
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
