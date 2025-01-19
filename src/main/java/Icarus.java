import java.util.ArrayList;
import java.util.Scanner;

public class Icarus {
    private ArrayList<String> list;

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

    private void addToList(String userInput) {
        list.add(userInput);
        System.out.println("I have added:");
        echo(userInput);
        readCommand();
    }

    private void returnList() {
        int i = 1;
        for (String item : list) {
            String str = i + ". " + item;
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
        } else {
            addToList(userInput);
        }
    }


    private void echo(String userInput) {
        System.out.println("\n" + userInput + "\n");
    }

    public static void main(String[] args) {
        Icarus chatbot = new Icarus();
    }
}
