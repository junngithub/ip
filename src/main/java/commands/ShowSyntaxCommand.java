package commands;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;

/**
 * TODO
 */
public class ShowSyntaxCommand extends Command {
    public ShowSyntaxCommand() {
        super();
    }

    /**
     * TODO
     * @param taskManager the TaskManager to manage tasks for the command..
     * @param ui the UI to interact with the user.
     * @param parser the Parser that processes the user input and creates the task.
     * @param store the {@link Storage} to manage task saving/loading (if needed by the command).
     * @return tODO
     */
    @Override
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store) {
        return "Here's your syntax: " + parser.saySyntax();
    }
}
