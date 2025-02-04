package Commands;
import java.util.ArrayList;

import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;
import Exceptions.InvalidNumberException;
import Tasks.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    public void execute(TaskManager taskManager, UI ui, Storage store) throws Exception {
        ArrayList<Task> list = taskManager.getList();
        String[] arr = super.getUserInput().split(" ", 2);
        int i = Integer.parseInt(arr[1]);
        int size = list.size();
        if (i > size) {
            throw new InvalidNumberException(i, size);
        }
        Task task = list.remove(i - 1);
        String message = "I have removed this item: \n" + task;
        System.out.println(message);
        taskManager.returnNumberOfItems();

    }
}
