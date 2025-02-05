package Tasks;

import Essentials.Parser;
import Exceptions.EmptyInputException;
import Exceptions.InvalidInputException;

public class Events extends Task {
    private String startDate;
    private String endDate;
    private String formattedStartDate;
    private String formattedEndDate;
    private final static String taskType = "event";

    private Events(String task, String startDate, String endDate, String formattedStartDate,
                   String formattedEndDate) {
        super(task, taskType);
        this.startDate = startDate;
        this.endDate = endDate;
        this.formattedStartDate = formattedStartDate;
        this.formattedEndDate = formattedEndDate;
    }

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

    @Override
    public String toFile() {
        return super.toFile() + " /from " + this.startDate + " /to " + this.endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.formattedStartDate
                + " to: " + this.formattedEndDate + ")";
    }
}
