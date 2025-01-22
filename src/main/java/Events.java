public class Events extends Task{
    private String startDate;
    private String endDate;
    public Events(String task, String startDate, String endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
