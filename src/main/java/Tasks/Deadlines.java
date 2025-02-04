package Tasks;

public class Deadlines extends Task {
    private String deadline;
    public Deadlines(String task, String deadline) {
        super(task, "deadline");
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
