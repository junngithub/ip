package Commands;

import java.util.ArrayList;

import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;
import Exceptions.InvalidNumberException;
import Tasks.Task;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String userInput) {
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
        task.markUndone();
        String message = "Sure, I have marked this as unfinished: \n" + task;
        System.out.println(message);
    }
}
