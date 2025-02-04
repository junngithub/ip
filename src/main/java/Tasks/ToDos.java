package Tasks;

public class ToDos extends Task {
    public ToDos(String task) {
        super(task, "todo");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
