package Tasks;

import Essentials.Parser;
import Exceptions.EmptyInputException;
import Exceptions.InvalidInputException;

/**
 * Represents an Event task with a specified start and end date.
 * Inherits from the Task class and provides functionality
 * for handling tasks with start and end dates.
 */
public class Events extends Task {
    private String startDate;
    private String endDate;
    private String formattedStartDate;
    private String formattedEndDate;
    private final static String taskType = "event";

    /**
     * Constructs an Event task object with a start and end date.
     *
     * @param task the description of the task.
     * @param startDate the raw start date string from user input.
     * @param endDate the raw end date string from user input.
     * @param formattedStartDate the formatted version of the start date.
     * @param formattedEndDate the formatted version of the end date.
     */
    private Events(String task, String startDate, String endDate, String formattedStartDate,
                   String formattedEndDate) {
        super(task, taskType);
        this.startDate = startDate;
        this.endDate = endDate;
        this.formattedStartDate = formattedStartDate;
        this.formattedEndDate = formattedEndDate;
    }

    /**
     * Factory method to create a new Events object from the provided input string.
     *
     * @param string the user input containing the task description, start date, and end date.
     * @param parser a parser used to format the start and end date strings into structured formats.
     * @return a new Events object.
     * @throws EmptyInputException if the input string is missing task description,
     * "/from" or "/to" keywords, or start or end dates.
     * @throws InvalidInputException if the input start or end dates' format is invalid.
     */
    public static Task of(String string, Parser parser)
            throws EmptyInputException, InvalidInputException {
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
        String[] datesArr = leftover.split("/to");
        if (datesArr.length == 1 || datesArr[1].isBlank()) {
            throw new EmptyInputException(taskType + " /to", "description");
        }
        String start = datesArr[0].trim();
        String end = datesArr[1].trim();
        return new Events(task, start, end, parser.parseTime(start), parser.parseTime(end));
    }

    /**
     * Returns a string representation of this task in a format suitable for saving to a file.
     * The format includes the task type, description, start date, and end date.
     *
     * @return the task details formatted for file storage.
     */
    @Override
    public String toFile() {
        return super.toFile() + " /from " + this.startDate + " /to " + this.endDate;
    }

    /**
     * Returns a string representation of this task, including the task type, status, description,
     * start date, and end date.
     *
     * @return a string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.formattedStartDate
                + " to: " + this.formattedEndDate + ")";
    }
}
