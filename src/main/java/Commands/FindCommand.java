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

    public void execute(TaskManager taskManager, UI ui, Parser parser, Storage store) throws EmptyInputException {
        String[] inputArr = super.getUserInput().split(" ");
        if (inputArr.length == 1) {
            throw new EmptyInputException("find", "description");
        }
        ArrayList<Task> list = taskManager.getList();
        if (list.isEmpty()) {
            System.out.println("You have no items in your list.");
        } else {
            System.out.println("Here is a list of Tasks that match your query:\n");
            int i = 1;
            for (Task item : list) {
                if (Pattern.compile(inputArr[1]).matcher(item.toString()).find()) {
                    String str = i + ". " + item.toString();
                    System.out.println(str);
                    i++;
                }
            }
        }
    }
}
