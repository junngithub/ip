import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Pattern;

public class Icarus {
    private ArrayList<Task> list;
    private final Pattern markPattern = Pattern.compile("mark [1-9][0-9]*$");
    private final Pattern unmarkPattern = Pattern.compile("unmark [1-9][0-9]*$");
    public Icarus(){
        this.list = new ArrayList<>();
        greet();
        readCommand();
    }

    private void greet() {
        String greeting = """ 
                -------------------------------------
                Greetings! My name is Icarus. \n
                How can I be of service to you today?
                -------------------------------------
                """;
        System.out.println(greeting);
    }

    private void sayBye() {
        String bye = """
                -------------------------------------
                Bye! See you next time, my friend.
                -------------------------------------
                """;
        System.out.println(bye);
    }

    private void markDone(String userInput) {
        int i = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task task = list.get(i);
        task.markDone();
        String message = "Very well, I have marked this as completed: \n" + task;
        System.out.println(message);
        System.out.println("\n");
        readCommand();
    }

    private void markUndone(String userInput) {
        int i = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task task = list.get(i);
        task.markUndone();
        String message = "Sure, I have marked this as unfinished: \n" + task;
        System.out.println(message);
        System.out.println("\n");
        readCommand();
    }

    private void addToList(String userInput) {
        Task task = new Task(userInput);
        list.add(task);
        System.out.println("I have added:");
        echo(userInput);
        readCommand();
    }

    private void returnList() {
        int i = 1;
        for (Task item : list) {
            String str = i + ". " + item.toString();
            System.out.println(str);
            i++;
        }
        System.out.println("\n");
        readCommand();
    }

    private void readCommand() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println("\n");
        if (userInput.equals("bye")) {
            sayBye();
        } else if (userInput.equals("list")) {
            returnList();
        } else if (markPattern.matcher(userInput).find()) {
            markDone(userInput);
        } else if (unmarkPattern.matcher(userInput).find()) {
            markUndone(userInput);
        } else {
            addToList(userInput);
        }
    }


    private void echo(String userInput) {
        System.out.println(userInput + "\n");
    }

    public static void main(String[] args) {
        Icarus chatbot = new Icarus();
    }
}
