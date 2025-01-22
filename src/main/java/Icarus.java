import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Pattern;

public class Icarus {
    private ArrayList<Task> list;
    private final Pattern markPattern = Pattern.compile("^mark [1-9][0-9]*$");
    private final Pattern unmarkPattern = Pattern.compile("^unmark [1-9][0-9]*$");
    private final Pattern taskPattern = Pattern.compile("^(todo|deadline|event)");
    private final String border = "---------------------------------------------";

    public Icarus(){
        this.list = new ArrayList<>();
        greet();
        readCommand();
    }

    private void greet() {
        String greeting = border +
                """ 
                \nGreetings! My name is Icarus.
                How can I be of service to you today?
                """ + border;
        System.out.println(greeting);
    }

    private void sayBye() {
        String bye = border + "\nBye! See you next time, my friend.\n" + border;
        System.out.println(bye);
    }

    private void markDone(String userInput) {
        int i = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task task = list.get(i);
        task.markDone();
        String message = "Very well, I have marked this as completed: \n" + task;
        System.out.println(message);
        System.out.println(border);
        readCommand();
    }

    private void markUndone(String userInput) {
        int i = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task task = list.get(i);
        task.markUndone();
        String message = "Sure, I have marked this as unfinished: \n" + task;
        System.out.println(message);
        System.out.println(border);
        readCommand();
    }

    private Task createTask(String userInput) {
        String[] arr = userInput.split(" ", 2);
        String taskType = arr[0];
        String string = arr[1];
        switch (taskType) {
            case "deadline":
                if (!string.contains("/by ")){
                    break;
                }
                String[] deadlineArr = string.split(" /by ");
                return new Deadlines(deadlineArr[0], deadlineArr[1]);
            case "event":
                if (!string.contains("/from ")){
                    break;
                }
                String task = string.split(" /from ")[0];
                String leftover = string.split(" /from ")[1];
                if (!string.contains("/to ")) {
                    break;
                }
                String[] stringArr = leftover.split(" /to ");
                return new Events(task, stringArr[0], stringArr[1]);
            case "todo":
                return new ToDos(userInput);
        }
        return null;
    }

    private void addToList(Task task) {
        list.add(task);
        System.out.println("I have added: \n" + task.toString());
        System.out.println(border);
        readCommand();
    }

    private void returnList() {
        int i = 1;
        for (Task item : list) {
            String str = i + ". " + item.toString();
            System.out.println(str);
            i++;
        }
        System.out.println(border);
        readCommand();
    }

    private void readCommand() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println(border);
        if (userInput.equals("bye")) {
            sayBye();
        } else if (userInput.equals("list")) {
            returnList();
        } else if (markPattern.matcher(userInput).find()) {
            markDone(userInput);
        } else if (unmarkPattern.matcher(userInput).find()) {
            markUndone(userInput);
        } else if (taskPattern.matcher(userInput).find()) {
            Task task = createTask(userInput);
            addToList(task);
        }
    }

    private void echo(String userInput) {
        System.out.println(userInput + "\n");
    }

    public static void main(String[] args) {
        Icarus chatbot = new Icarus();
    }
}
