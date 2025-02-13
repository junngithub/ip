package Commands;

import java.util.ArrayList;
import java.util.regex.Pattern;

import Essentials.Parser;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;
import Exceptions.EmptyInputException;
import Tasks.Task;

public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

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
