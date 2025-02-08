package Commands;
import java.util.ArrayList;

import Essentials.Parser;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;
import Exceptions.InvalidInputException;
import Tasks.Task;

/**
 * Represents a DeleteCommand command that deletes a task from the task list.
 * Inherits from Command and provides functionality to process user input,
 * validate the task index, and remove the corresponding task from the TaskManager.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand with the specified user input.
     *
     * @param userInput the input provided by the user, which specifies the task to be deleted.
     */
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes the command by parsing the user input, verifying the task index,
     * removing the corresponding task from the TaskManager, and displaying a confirmation message.
     *
     * @param taskManager the TaskManager to manage tasks and remove the task.
     * @param ui the UI to interact with the user.
     * @param parser the Parser to process the user input (not used in this method).
     * @param store the Storage for saving or loading task data (not used in this method).
     * @throws InvalidInputException if the given index is out of bounds.
     */
    public void execute(TaskManager taskManager, UI ui, Parser parser, Storage store)
            throws InvalidInputException {
        ArrayList<Task> list = taskManager.getList();
        String[] arr = super.getUserInput().split(" ", 2);
        int i = Integer.parseInt(arr[1]);
        int size = list.size();
        if (i > size) {
            throw new InvalidInputException(i, size);
        }
        Task task = list.remove(i - 1);
        String message = "I have removed this item: \n" + task + "\n";
        System.out.println(message);
        taskManager.sayNumberOfItems();

    }
}
