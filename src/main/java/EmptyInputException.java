public class EmptyInputException extends Exception{
    private String taskType;
    private String problem;
    public EmptyInputException(String taskType, String problem) {
        super();
        this.taskType = taskType;
        if (problem.equals("description")) {
            this.problem = " description cannot be empty.";
        } else if (problem.equals("number")) {
            this.problem = " requires a number.";
        } else if (problem.equals("missing by")) {
            this.problem = " task requires '/by'.";
        } else if (problem.equals("missing from")) {
            this.problem = " task requires '/from'.";
        } else if (problem.equals("missing to")) {
            this.problem = " task requires '/to'.";
        }
    }

    @Override
    public String toString() {
        return taskType + problem;
    }
}
