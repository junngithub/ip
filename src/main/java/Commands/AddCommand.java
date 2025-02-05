package Commands;

import Essentials.Parser;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;
import Exceptions.EmptyInputException;
import Exceptions.InvalidInputException;
import Tasks.Task;

public class AddCommand extends Command {
    public AddCommand(String userInput) {
        super(userInput);
    }

    public void execute(TaskManager taskManager, UI ui, Parser parser, Storage store)
            throws EmptyInputException, InvalidInputException {
        Task task = parser.createTask(super.getUserInput());
        taskManager.addToList(task, true);
        taskManager.returnNumberOfItems();
    }


}
