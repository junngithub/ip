package commands;

import java.util.ArrayList;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;
import exceptions.InvalidInputException;
import tasks.Task;

/**
 * Represents a MarkCommand command that marks a task as completed.
 * Inherits from Command and provides functionality to process user input,
 * validate the task index, and mark the corresponding task as done in the TaskManager.
 */
public class MarkCommand extends Command {
    public MarkCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes the command by parsing the user input, verifying the task index,
     * and marking the corresponding task as completed in the TaskManager.
     * A confirmation message is displayed upon success.
     *
     * @param taskManager the TaskManager containing the task will be marked as completed.
     * @param ui the UI to format the response with clear paragraph separation.
     * @param parser the Parser to process the user input (not used in this method).
     * @param store the Storage for saving or loading task data (not used in this method).
     * @return a string response that confirms the task was marked as completed.
     * @throws InvalidInputException  if the given index is out of bounds.
     */
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store)
            throws InvalidInputException {
        ArrayList<Task> list = taskManager.getList();
        String[] arr = super.getUserInput().split(" ", 2);
        int i = Integer.parseInt(arr[1]);
        int size = list.size();
        if (i > size) {
            throw new InvalidInputException(i, size);
        }
        Task task = list.get(i - 1);
        task.markDone();
        return "Very well, I have marked this as completed: \n" + ui.showBorder() + task + "\n";
    }
}
