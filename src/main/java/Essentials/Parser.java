package Essentials;

import java.util.regex.Pattern;

import Commands.AddCommand;
import Commands.Command;
import Commands.DeleteCommand;
import Commands.ExitCommand;
import Commands.ListCommand;
import Commands.MarkCommand;
import Commands.UnmarkCommand;
import Exceptions.EmptyInputException;
import Exceptions.InvalidNumberException;
import Exceptions.NotACommandException;

public class Parser {
    private final Pattern hasNumberPattern = Pattern.compile("^(mark|unmark|delete)$");
    private final Pattern validMarkPattern = Pattern.compile("^mark [1-9][0-9]*$");
    private final Pattern validUnmarkPattern = Pattern.compile("^unmark [1-9][0-9]*$");
    private final Pattern validDeletePattern = Pattern.compile("^delete [1-9][0-9]*$");
    private final Pattern taskPattern = Pattern.compile("^(todo|deadline|event)");

    public Parser() {
    }

    public Command parseCommand(String userInput) {
        try {
            if (userInput.equals("bye")) {
                return new ExitCommand();
            } else if (userInput.equals("list")) {
                return new ListCommand(userInput);
            } else if (hasNumberPattern.matcher(userInput.split(" ")[0]).find()) {
                if (validUnmarkPattern.matcher(userInput).find()) {
                    return new UnmarkCommand(userInput);
                } else if (validMarkPattern.matcher(userInput).find()) {
                    return new MarkCommand(userInput);
                } else if (validDeletePattern.matcher(userInput).find()) {
                    return new DeleteCommand(userInput);
                } else {
                    throw new EmptyInputException(userInput.split(" ")[0], "number");
                }
            } else if (taskPattern.matcher(userInput).find()) {
                return new AddCommand(userInput);
            } else {
                throw new NotACommandException();
            }
        } catch (NotACommandException | EmptyInputException exception) {
            System.out.println(exception);
        }
        return null;
    }


}
