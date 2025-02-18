package essentials;

import java.util.ArrayList;

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
     * Displays the number of tasks currently in the list.
     * Outputs a message indicating how many tasks are in the list.
     */
    public String sayNumberOfItems() {
        return "You have " + list.size() + " item(s) in your list.\n";
    }

    /**
     * Adds a new task to the list. If toCall is true, a confirmation message
     * with the task details is printed.
     *
     * @param task the task to be added to the list.
     */
    public String sayTaskAddedToList(Task task) {
        assert task != null;
        list.add(task);
        return "I have added: \n" + task.toString() + "\n";
    }

    /**
     * TODO
     * @param task TODO
     */
    public void addToList(Task task) {
        assert task != null;
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
