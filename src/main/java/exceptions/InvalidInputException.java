package exceptions;

/**
 * Represents an exception that is thrown when an invalid input is encountered.
 * This can occur due to an invalid date/time format or an attempt to access an item
 * that does not exist in the task list.
 */
public class InvalidInputException extends Exception {
    private int index;
    private int size;
    private boolean isDate;
    private boolean isDueToSet;
    private String userInput = "";

    /**
     * Constructs an InvalidInputException for invalid access to a task list.
     * This is thrown when the user tries to access an item that does not exist
     * in the task list.
     *
     * @param index the index of the item that was attempted to be accessed.
     * @param size the total number of items in the task list.
     */
    public InvalidInputException(int index, int size) {
        super();
        this.isDate = false;
        this.index = index;
        this.size = size;
    }

    /**
     * Constructs an InvalidInputException for invalid date and/or time input.
     * This is thrown when the user provides an invalid date or time format.
     */
    public InvalidInputException() {
        this.isDate = true;
    }

    /**
     * TODO
     * @param userInput TODO
     * @param isDueToSet TODO
     */
    public InvalidInputException(String userInput, boolean isDueToSet) {
        this.userInput = userInput;
        this.isDueToSet = isDueToSet;
    }

    /**
     * Returns a string representation of the exception, providing details about the error.
     * If the error is related to date/time, it will indicate the issue with the provided
     * date or time. If it is related to accessing an invalid task,
     * it will indicate the invalid index and the number of items in the list.
     *
     * @return a string representation of the exception with a message based on the error type.
     */
    @Override
    public String toString() {
        String header = "I am unable to act on this request.\n";
        if (isDate) {
            return header + "Date and/or Time provided is invalid.\n";
        } else if (!userInput.isEmpty()) {
            if (isDueToSet) {
                return header + this.userInput + " is not a valid command to be altered.";
            }
            return header + this.userInput + " is already a command.";
        }
        return header + "You are trying to access item number " + this.index
                + ".\nBut there are " + this.size + " item(s) in your list.\n";
    }
}
