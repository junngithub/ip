package commands;

import java.util.ArrayList;

import essentials.Parser;
import essentials.Storage;
import essentials.TaskManager;
import essentials.UI;
import tasks.Task;

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
    public String execute(TaskManager taskManager, UI ui, Parser parser, Storage store) {
        ArrayList<Task> list = taskManager.getList();
        if (list.isEmpty()) {
            return "You have no items in your list.\n";
        } else {
            StringBuilder response = new StringBuilder("Here is your list:\n");
            response.append(ui.showBorder());
            int i = 1;
            for (Task item : list) {
                response.append(i).append(". ").append(item.toString()).append("\n");
                i++;
            }
            return response.toString();
        }
    }
}
