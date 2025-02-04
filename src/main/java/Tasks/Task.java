package Tasks;

public abstract class Task {
    private String task;
    private String taskType;
    private boolean isDone;
    public Task(String task, String taskType) {
        this.task = task;
        this.isDone = false;
        this.taskType = taskType;
    }

    private String getStatus() {
        String str = " ";
        if (isDone) {
            str = "X";
        }
        return str;
    }

    @Override
    public String toString(){
        return "[" + getStatus() + "] " + task;
    }

    public String toFile() {
        int i;
        if (isDone) {
            i = 1;
        } else {
            i = 0;
        }
        return i + " " + taskType + " " + task;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone(){
        this.isDone = false;
    }
}
