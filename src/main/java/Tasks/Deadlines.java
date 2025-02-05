package Tasks;

import Essentials.Parser;
import Exceptions.EmptyInputException;

public class Deadlines extends Task {
    private String deadline;
    private final static String taskType = "deadline";

    private Deadlines(String task, String deadline) {
        super(task, taskType);
        this.deadline = deadline;
    }

    public static Task of(String string, Parser parser) throws EmptyInputException {
        if (!string.contains("/by")) {
            throw new EmptyInputException(taskType, "missing by");
        }
        String[] deadlineArr = string.split("/by");
        if (deadlineArr.length == 1 || deadlineArr[1].isBlank()) {
            throw new EmptyInputException(taskType + " /by", "description");
        }
        return new Deadlines(deadlineArr[0].trim(), deadlineArr[1].trim());
    }

    @Override
    public String toFile() {
        return super.toFile() + " /by " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
