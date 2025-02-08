package Tasks;

/**
 * Represents a To-Do task. Inherits from the Task class and provides functionality
 * for handling tasks without a specific deadline or date.
 */

public class ToDos extends Task {
    private final static String taskType = "todo";

    /**
     * Constructs a To-Do task object.
     *
     * @param task the description of the To-Do task.
     */

    private ToDos(String task) {
        super(task, taskType);
    }

    /**
     * Factory method to create a new ToDos object from the provided task description.
     *
     * @param task the description of the task to be created.
     * @return a new ToDos object.
     */

    public static Task of(String task) {
        return new ToDos(task);
    }



    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
