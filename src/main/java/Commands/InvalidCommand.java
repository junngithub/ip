package Commands;

import Essentials.Parser;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;

public class InvalidCommand extends Command{
    public InvalidCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store) throws Exception {
        return super.getUserInput();
    }
}
