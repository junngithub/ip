package Tasks;

/**
 * Represents a generic task with a description, status, and type.
 * This is an abstract class that can be extended by specific task types such as
 * To-Dos, Deadlines, and Events.
 */
public abstract class Task {
    private String task;
    private String type;
    private boolean isDone;

    /**
     * Constructs a Task object with a given description and task type.
     *
     * @param task the description of the task.
     * @param taskType the type of the task (e.g., "to-do", "deadline", "event").
     */
    public Task(String task, String taskType) {
        this.task = task;
        this.isDone = false;
        this.type = taskType;
    }

    /**
     * Returns the current status of the task as a string.
     * If the task is marked as done, the status will be "X", otherwise it will be a space (" ").
     *
     * @return the status of the task.
     */
    private String getStatus() {
        String str = " ";
        if (isDone) {
            str = "X";
        }
        return str;
    }

    /**
     * Returns a string representation of the task, including the task's status and description.
     *
     * @return a string representation of the task in the format: "[status] description".
     */
    @Override
    public String toString(){
        return "[" + getStatus() + "] " + task;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     * The format includes the task's completion status (1 for done, 0 for not done),
     * task type (e.g., "to-do", "deadline", "event"), and the task description.
     *
     * @return a string representing the task in a file-compatible format.
     */
    public String toFile() {
        int i;
        if (isDone) {
            i = 1;
        } else {
            i = 0;
        }
        return i + " " + type + " " + task;
    }

    /**
     * Marks the task as completed (done).
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed (undone).
     */
    public void markUndone(){
        this.isDone = false;
    }
}
