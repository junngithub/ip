public class Task {
    private String task;
    private boolean isDone;
    public Task(String task) {
        this.task = task;
        this.isDone = false;
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

    public void markDone() {
        this.isDone = true;
    }

    public  void markUndone(){
        this.isDone = false;
    }
}
