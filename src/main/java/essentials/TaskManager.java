package essentials;

import java.util.ArrayList;

import exceptions.InvalidInputException;
import tasks.Task;

/**
 * The TaskManager class is responsible for managing a list of tasks.
 * It provides functionality to add tasks to the list, retrieve the list of tasks,
 * and display the current number of tasks.
 */
public class TaskManager {
    private ArrayList<Task> list;


    /**
     * Constructs a TaskManager with an empty task list.
     * Initializes the list where tasks will be stored.
     */
    public TaskManager() {
        this.list = new ArrayList<>();
    }

    /**
     * Outputs a message indicating how many tasks are in the list.
     *
     * @return a string indicating the number of tasks.
     */
    public String sayNumberOfItems() {
        return "You have " + list.size() + " item(s) in your list.\n";
    }

    /**
     * Adds a new task to the list and returns a confirmation message.
     * If successful, a string with the task details is returned.
     *
     * @param task the task to be added to the list.
     * @return a string containing the task details.
     * @throws InvalidInputException if the task is already in the list.
     */
    public String sayTaskAddedToList(Task task) throws InvalidInputException {
        assert task != null;
        addToList(task);
        return task.toString() + "\n";
    }

    /**
     * Adds a task to the internal list of tasks.
     * Throws an InvalidInputException if the task already exists in the list.
     *
     * @param task the task to be added to the list.
     * @throws InvalidInputException if the task already exists in the list.
     */
    public void addToList(Task task) throws InvalidInputException {
        if (list.contains(task)) {
            throw new InvalidInputException(task.getTask(), false, true);
        }
        list.add(task);
    }

    /**
     * Returns the current list of tasks.
     *
     * @return the list of tasks managed by the TaskManager.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }
}
