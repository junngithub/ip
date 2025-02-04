package Commands;

import java.util.ArrayList;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;
import Exceptions.InvalidNumberException;
import Tasks.Task;

public class MarkCommand extends Command {
    public MarkCommand(String userInput) {
        super(userInput);
    }

    public void execute(TaskManager taskManager, UI ui, Storage store)
            throws InvalidNumberException {
        ArrayList<Task> list = taskManager.getList();
        String[] arr = super.getUserInput().split(" ");
        int i = Integer.parseInt(arr[1]);
        int size = list.size();
        if (i > size) {
            throw new InvalidNumberException(i, size);
        }
        Task task = list.get(i - 1);
        task.markDone();
        String message = "Very well, I have marked this as completed: \n" + task;
        System.out.println(message);
    }
}


