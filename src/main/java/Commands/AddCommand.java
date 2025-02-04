package Commands;

import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;
import Exceptions.EmptyInputException;
import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;

public class AddCommand extends Command{
    public AddCommand(String userInput) {
        super(userInput);
    }

    public void execute(TaskManager taskManager, UI ui, Storage store) throws EmptyInputException {
        Task task = createTask(super.getUserInput());
        taskManager.addToList(task, true);
        taskManager.returnNumberOfItems();
    }

    public static Task createTask(String userInput) throws EmptyInputException {
        String[] arr = userInput.split(" ", 2);
        String taskType = arr[0];
        if (arr.length == 1 || arr[1].trim().charAt(0) == '/') {
            throw new EmptyInputException(taskType, "description");
        }
        String string = arr[1];
        switch (taskType) {
        case "deadline":
            if (!string.contains("/by")) {
                throw new EmptyInputException(taskType, "missing by");
            }
            String[] deadlineArr = string.split("/by");
            if (deadlineArr.length == 1 || deadlineArr[1].isBlank()) {
                throw new EmptyInputException(taskType + " /by", "description");
            }
            return new Deadlines(deadlineArr[0].trim(), deadlineArr[1].trim());
        case "event":
            if (!string.contains("/from")) {
                throw new EmptyInputException(taskType, "missing from");
            }
            String[] eventArr = string.split("/from");
            if (eventArr.length == 1 || eventArr[1].isBlank()) {
                throw new EmptyInputException(taskType + " /from", "description");
            }
            String task = eventArr[0].trim();
            String leftover = eventArr[1];
            if (!leftover.contains("/to")) {
                throw new EmptyInputException(taskType, "missing to");
            }
            String[] stringArr = leftover.split("/to");
            if (stringArr.length == 1 || stringArr[1].isBlank()) {
                throw new EmptyInputException(taskType + " /to", "description");
            }
            return new Events(task, stringArr[0].trim(), stringArr[1].trim());
        case "todo":
            return new ToDos(string);
        }
        return null;
    }
}
