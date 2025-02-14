package Tasks;

import Essentials.Parser;
import Exceptions.EmptyInputException;
import Exceptions.InvalidInputException;

/**
 * Represents a Deadline task with a specified deadline.
 * Inherits from the Task class and provides functionality
 * for handling tasks with deadlines.
 */
public class Deadlines extends Task {
    private String deadline;
    private String formattedDeadline;
    private final static String taskType = "deadline";

    /**
     * Constructs a Deadlines task object.
     *
     * @param task the description of the task.
     * @param deadline the raw deadline string from user input.
     * @param formattedDeadline the formatted version of the deadline.
     */
    private Deadlines(String task, String deadline, String formattedDeadline) {
        super(task, taskType);
        this.deadline = deadline;
        this.formattedDeadline = formattedDeadline;
    }

    /**
     * Factory method to create a new Deadlines object from the provided input string.
     *
     * @param string the user input containing the task description and deadline.
     * @param parser a parser used to format the deadline string into a structured format.
     * @return a new Deadlines object.
     * @throws EmptyInputException if the input string is missing description of the task,
     * the "/by" keyword or the deadline.
     * @throws InvalidInputException if the deadline format is invalid.
     */
    public static Task of(String string, Parser parser) throws EmptyInputException, InvalidInputException {
        if (!string.contains("/by")) {
            throw new EmptyInputException(taskType, "missing by");
        }
        String[] deadlineArr = string.split("/by");
        if (deadlineArr.length == 1 || deadlineArr[1].isBlank()) {
            throw new EmptyInputException(taskType + " /by", "description");
        }
        String time = deadlineArr[1].trim();
        return new Deadlines(deadlineArr[0].trim(), time, parser.parseTime(time));
    }

    /**
     * Returns a string representation of this task in a format suitable for saving to a file.
     * The format includes the task type, description, and the deadline.
     *
     * @return the task details formatted for file storage.
     */
    @Override
    public String toFile() {
        return super.toFile() + " /by " + this.deadline;
    }

    /**
     * Returns a string representation of this task, including the task type, status,
     * description and the formatted deadline.
     *
     * @return a string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}
