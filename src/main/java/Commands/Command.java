package Commands;

import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;

public abstract class Command {
    private String userInput;

    public Command() {
    }

    public Command(String userInput) {
        this.userInput = userInput;
    }

    public abstract void execute (TaskManager taskManager, UI ui, Storage store)
            throws Exception;

    public boolean isExiting() {
        return false;
    }
    public String getUserInput() {
        return userInput;
    }
}
