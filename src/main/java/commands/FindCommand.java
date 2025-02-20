package commands;

import java.util.ArrayList;
import java.util.regex.Pattern;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;
import exceptions.EmptyInputException;
import tasks.Task;

/**
 * Represents a FindCommand command that allows the user to search for tasks in their task list
 * based on a keyword or phrase in the task's description.
 */
public class FindCommand extends Command {

    /**
     * Constructs a FindCommand with the provided user input string.
     *
     * @param userInput the input string provided by the user containing the find keyword
     *     and the query for the task description.
     */
    public FindCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes the find command, searching for tasks in the task list that match the query
     * provided in the user input.
     *
     * @param taskManager the TaskManager that manages the list of tasks to be searched through.
     * @param ui the UI to format the response with clear paragraph separation.
     * @param parser the Parser that processes the user input (not used in this method).
     * @param store the Storage to manage task saving/loading (not used in this method.
     * @return a string indicating the result of the search, including matching tasks.
     * @throws EmptyInputException if the user input does not include a query string.
     */
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store)
            throws EmptyInputException {
        String[] inputArr = super.getUserInput().split(" ");
        if (inputArr.length == 1) {
            throw new EmptyInputException("find", "description");
        }
        ArrayList<Task> list = taskManager.getList();
        if (list.isEmpty()) {
            return "You have no items in your list.\n";
        } else {
            StringBuilder response = new StringBuilder("Here is a list of Tasks that match your query:\n");
            response.append(ui.showBorder());
            int i = 1;
            for (Task item : list) {
                if (Pattern.compile(inputArr[1]).matcher(item.toString()).find()) {
                    response.append(i).append(". ").append(item.toString()).append("\n");
                    i++;
                }
            }
            return response.toString();
        }
    }
}
