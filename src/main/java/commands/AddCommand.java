package commands;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;
import exceptions.EmptyInputException;
import exceptions.InvalidInputException;
import tasks.Task;


/**
 * Represents an AddCommand command class that adds a new task to the task list.
 * Inherits from Command and provides functionality to process user input,
 * create a new task, and add it to the TaskManager.
 */
public class AddCommand extends Command {
    public AddCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes the command by parsing the user input to create a new task, adding
     * the task to the TaskManager, and displaying the updated task count.
     *
     * @param taskManager the TaskManager where the task will be added.
     * @param ui the UI to interact with the user.
     * @param parser the Parser that processes the user input and creates the task.
     * @param store the Storage for saving or loading task data (not used in this method).
     * @throws EmptyInputException if the user input is missing a description of the task,
     *     keywords, dates or times
     * @throws InvalidInputException if the user input's format is invalid.
     */
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store)
            throws EmptyInputException, InvalidInputException {
        Task task = parser.createTask(super.getUserInput());
        String response = taskManager.sayTaskAddedToList(task);
        response += taskManager.sayNumberOfItems();
        return response;
    }


}
