package Essentials;

import java.util.Scanner;

public class UI {
    private final String border = "--------------------------------------------------";
    public UI() {
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void greet() {
        String greeting = border +
                """ 
                \nGreetings! My name is Icarus.
                How can I be of service to you today?
                """ + border;
        System.out.println(greeting);
    }
}
