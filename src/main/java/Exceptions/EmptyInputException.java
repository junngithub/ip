package Exceptions;

/**
 * Represents an exception that is thrown when there is a missing or incorrect input
 * for a task, such as an empty description or missing date/time information.
 */
public class EmptyInputException extends Exception{
    private String taskType;
    private String problem;

    /**
     * Constructs an EmptyInputException with a specific task type and problem description.
     * This exception is used to indicate issues with task input, such as missing description,
     * missing dates, or incorrect formatting.
     *
     * @param taskType the type of task (e.g., "to-do", "deadline", "event").
     * @param problem a specific problem related to the input
     * (e.g. "description", "missing by", "missing from").
     */
    public EmptyInputException(String taskType, String problem) {
        super();
        this.taskType = taskType;
        switch (problem) {
        case "description" -> this.problem = " description cannot be empty.\n";
        case "number" -> this.problem = " requires a number.\n";
        case "missing by" -> this.problem = " task requires '/by'.\n";
        case "missing from" -> this.problem = " task requires '/from'.\n";
        case "missing to" -> this.problem = " task requires '/to'.\n";
        }
    }

    /**
     * Returns a string representation of the exception, including the task type
     * and a detailed description of the problem.
     *
     * @return a string describing the error, such as "deadline description cannot be empty."
     */
    @Override
    public String toString() {
        return taskType + problem;
    }
}
