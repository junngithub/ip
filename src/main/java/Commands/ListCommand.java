package Commands;

import java.util.ArrayList;

import Essentials.Parser;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;
import Tasks.Task;

/**
 * Represents a ListCommand command that lists all tasks in the task list.
 * Inherits from Command and provides functionality to display all tasks
 * currently in the TaskManager.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    /**
     * Executes the command by displaying all tasks currently in the TaskManager.
     * If no tasks are present, a message indicating that the list is empty will be shown.
     *
     * @param taskManager the TaskManager to retrieve the list of tasks.
     * @param ui the UI to interact with the user.
     * @param parser the Parser to process the user input (not used in this method).
     * @param store the Storage for saving or loading task data (not used in this method).
     */
    public void execute(TaskManager taskManager, UI ui, Parser parser, Storage store) {
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