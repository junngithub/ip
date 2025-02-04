package Exceptions;

public class InvalidNumberException extends Exception{
    private int index;
    private int size;
    public InvalidNumberException(int index, int size) {
        super();
        this.index = index;
        this.size = size;
    }

    @Override
    public String toString() {
        return "I am unable to act on this request." +
                "\nYou are trying to access item number " + this.index +
                ".\nBut there are " + this.size + " item(s) in your list.";
    }
}
