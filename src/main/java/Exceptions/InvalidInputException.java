package Exceptions;

public class InvalidInputException extends Exception{
    private int index;
    private int size;
    private boolean isDate;
    public InvalidInputException(int index, int size) {
        super();
        this.isDate = false;
        this.index = index;
        this.size = size;
    }
    public InvalidInputException() {
        this.isDate = true;
    }

    @Override
    public String toString() {
        String header = "I am unable to act on this request.\n";
        if (isDate) {
          return header + "Date and/or Time provided is invalid.";
        }
        return  header + "You are trying to access item number " + this.index +
                ".\nBut there are " + this.size + " item(s) in your list.";
    }
}
