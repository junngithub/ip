package Tasks;

import Essentials.Parser;
import Exceptions.EmptyInputException;

public class Events extends Task {
    private String startDate;
    private String endDate;
    private final static String taskType = "event";

    private Events(String task, String startDate, String endDate) {
        super(task, taskType);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Task of(String string, Parser parser) throws EmptyInputException {
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
        return new Events(task, datesArr[0].trim(), datesArr[1].trim());
    }

    @Override
    public String toFile() {
        return super.toFile() + " /from " + this.startDate + " /to " + this.endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
