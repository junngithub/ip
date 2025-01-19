import java.util.Scanner;

public class Icarus {
    public static void greet() {
        String greeting = """ 
                ----------------------
                Hello! I am Icarus. \n
                What can I do for you?
                ----------------------
                """;
        System.out.println(greeting);
    }

    public static void sayBye() {
        String bye = """
                -----------------------
                Bye! See you next time!
                -----------------------
                """;
        System.out.println(bye);
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if (userInput.equals("bye")) {
            sayBye();
        } else {
            System.out.println("\n" + userInput + "\n");
            echo();
        }

    }

    public static void main(String[] args) {
        greet();
        echo();
    }
}
