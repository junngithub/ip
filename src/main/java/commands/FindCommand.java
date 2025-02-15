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
 * TODO
 */
public class FindCommand extends Command {
    public FindCommand(String userInput) {
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
     */
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store)
            throws EmptyInputException {
        String[] inputArr = super.getUserInput().split(" ");
        if (inputArr.length == 1) {
            throw new EmptyInputException("find", "description");
        }
        ArrayList<Task> list = taskManager.getList();
        if (list.isEmpty()) {
            return "You have no items in your list.";
        } else {
            StringBuilder response = new StringBuilder("Here is a list of Tasks that match your query:\n");
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
