package Tasks;

import Essentials.Parser;
import Exceptions.EmptyInputException;
import Exceptions.InvalidInputException;

public class Deadlines extends Task {
    private String deadline;
    private String formattedDeadline;
    private final static String taskType = "deadline";

    private Deadlines(String task, String deadline, String formattedDeadline) throws InvalidInputException {
        super(task, taskType);
        this.deadline = deadline;
        this.formattedDeadline = formattedDeadline;
    }

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

    @Override
    public String toFile() {
        return super.toFile() + " /by " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}
