package Commands;

import java.util.ArrayList;

import Essentials.Parser;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;
import Tasks.Task;

public class ListCommand extends Command {
    public ListCommand(String userInput) {
        super(userInput);
    }

    public void execute(TaskManager taskManager, UI ui, Parser parser, Storage store) throws Exception {
        ArrayList<Task> list = taskManager.getList();
        if (list.isEmpty()) {
            System.out.println("You have no items in your list.");
        } else {
            System.out.println("Here is your list:");
            int i = 1;
            for (Task item : list) {
                String str = i + ". " + item.toString();
                System.out.println(str);
                i++;
            }
        }
    }
}