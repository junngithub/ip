package Commands;

import java.util.ArrayList;

import Essentials.Parser;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;
import Exceptions.InvalidInputException;
import Tasks.Task;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String userInput) {
        super(userInput);
    }

    public void execute(TaskManager taskManager, UI ui, Parser parser, Storage store)
            throws InvalidInputException {
        ArrayList<Task> list = taskManager.getList();
        String[] arr = super.getUserInput().split(" ", 2);
        int i = Integer.parseInt(arr[1]);
        int size = list.size();
        if (i > size) {
            throw new InvalidInputException(i, size);
        }
        Task task = list.get(i - 1);
        task.markUndone();
        String message = "Sure, I have marked this as unfinished: \n" + task;
        System.out.println(message);
    }
}
