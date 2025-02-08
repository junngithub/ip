package Essentials;

import java.util.Scanner;

/**
 * The UI class is responsible for handling user interactions through the console.
 * It provides methods for reading user input, displaying greetings, and showing borders
 * in the console for better visual organization.
 */
public class UI {
    private final String border = "--------------------------------------------------";
    /**
     * Constructs a UI object.
     */
    public UI() {
    }

    /**
     * Reads a single line of input from the user.
     *
     * @return the user input as a String.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays a greeting message to the user, introducing the program.
     * The message is framed with borders for better visual appearance.
     */
    public void greet() {
        String greeting = border +
                """ 
                \nGreetings! My name is Icarus.
                How can I be of service to you today?
                """ + border;
        System.out.println(greeting);
    }

    /**
     * Displays a border on the console. This border is used for visual separation
     * of different sections in the UI.
     */
    public void showBorder() {
        System.out.println(border);
    }
}
