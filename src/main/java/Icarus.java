public class Duke {
    public String greet() {
        return """ 
                ---------------------- \n
                Hello! I am Icarus. \n
                What can I do for you?
                ---------------------- \n
                """;
    }

    public String sayBye() {
        return "Bye! See you next time!";
    }
    public static void main(String[] args) {
        System.out.println(greet());
    }
}
