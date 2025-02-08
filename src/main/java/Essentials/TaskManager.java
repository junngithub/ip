package Essentials;

import java.util.ArrayList;

import Tasks.Task;

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
    public void sayNumberOfItems() {
        System.out.println("You have " + list.size() + " item(s) in your list.");
    }

    /**
     * Adds a new task to the list. If toCall is true, a confirmation message
     * with the task details is printed.
     *
     * @param task the task to be added to the list.
     * @param toCall whether to print a confirmation message after adding the task.
     */
    public void addToList(Task task, boolean toCall) {
        list.add(task);
        if (toCall) {
            System.out.println("I have added: \n" + task.toString() + "\n");
        }
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
