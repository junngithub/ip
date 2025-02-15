package commands;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;

/**
 * TODO
 */
public class InvalidCommand extends Command {
    public InvalidCommand(String userInput) {
        super(userInput);
    }

    /**
     * TODO
     * @param taskManager the TaskManager to manage tasks for the command..
     * @param ui the UI to interact with the user.
     * @param parser the Parser that processes the user input and creates the task.
     * @param store the {@link Storage} to manage task saving/loading (if needed by the command).
     * @return TODO
     * @throws Exception TODO
     */
    @Override
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store) throws Exception {
        return super.getUserInput();
    }
}
