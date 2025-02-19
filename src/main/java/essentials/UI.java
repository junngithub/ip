package essentials;

/**
 * The UI class is responsible for handling user interactions through the console.
 * It provides methods for reading user input, displaying greetings, and showing borders
 * in the console for better visual organization.
 */
public class UI {
    /**
     * Constructs a UI object.
     */
    public UI() {
    }

    /**
     * Displays a greeting message to the user, introducing the program.
     * The message is framed with borders for better visual appearance.
     */
    public String greet() {
        return showBorder() + """
                Greetings! My name is Icarus.
                How can I be of service to you today?
                """ + showBorder();
    }

    /**
     * Displays a border on the console. This border is used for visual separation
     * of different sections in the UI.
     */
    public String showBorder() {
        return "--------------------------------------------------\n";
    }
}
