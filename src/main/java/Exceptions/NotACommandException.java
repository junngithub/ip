package Exceptions;

/**
 * Represents an exception that is thrown when an unrecognized command is encountered.
 * This exception is used when the user input does not correspond to a valid command
 * that the Icarus.Icarus chatbot can process.
 */
public class NotACommandException extends Exception {

    /**
     * Constructs a NotACommandException.
     */
    public NotACommandException(){
        super();
    }

    /**
     * Returns a string representation of the exception, providing a message
     * indicating that the Icarus.Icarus chatbot cannot process the given request.
     *
     * @return a string indicating that the Icarus.Icarus chatbot is unable to assist with the request.
     */
    @Override
    public String toString() {
        return "Apologies, I am unable to assist with that request.\n";
    }
}
