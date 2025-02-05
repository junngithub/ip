package Tasks;

public class ToDos extends Task {
    private final static String taskType = "todo";
    private ToDos(String task) {
        super(task, taskType);
    }

    public static Task of(String task) {
        return new ToDos(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
