package Essentials;

import java.util.ArrayList;

import Exceptions.EmptyInputException;
import Exceptions.InvalidNumberException;
import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;

public class TaskManager {
    private ArrayList<Task> list;
    public TaskManager() {
        this.list = new ArrayList<>();
    }

    public void returnNumberOfItems() {
        System.out.println("You have " + list.size() + " item(s) in your list.");
    }

    public void addToList(Task task, boolean toCall) {
        list.add(task);
        if (toCall) {
            System.out.println("I have added: \n" + task.toString());
        }
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
