package essentials;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.EmptyInputException;
import exceptions.InvalidInputException;
import exceptions.NotACommandException;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.ToDos;

/**
 * A parser class responsible for parsing user input and converting it into commands
 * and tasks. It also handles file input and date/time parsing.
 */
public class Parser {
    private final Pattern hasNumberPattern = Pattern.compile("^(mark|unmark|delete)$");
    private final Pattern hasKeywordPattern = Pattern.compile("^find$");
    private final Pattern validMarkPattern = Pattern.compile("^mark [1-9][0-9]*$");
    private final Pattern validUnmarkPattern = Pattern.compile("^unmark [1-9][0-9]*$");
    private final Pattern validDeletePattern = Pattern.compile("^delete [1-9][0-9]*$");
    private final Pattern taskPattern = Pattern.compile("^(todo|deadline|event)");
    private final Pattern dateFirstPattern = Pattern.compile("^[0-9]{4}(-[0-9]{2}){2}( [0-9]{2}:[0-9]{2})*$");
    private final Pattern timeFirstPattern = Pattern.compile("^[0-9]{2}:[0-9]{2}( [0-9]{4}(-[0-9]{2}){2})*$");

    /**
     * Constructs a Parser instance.
     */
    public Parser() {
    }

    /**
     * Parses an input string and returns the corresponding command.
     * Recognises various commands such as "bye", "list", "mark", "unmark", "delete",
     * and task-related commands. Catches any exceptions thrown when creating commands
     * or when command given is not recognised.
     *
     * @param userInput the input provided by the user.
     * @return the corresponding command object.
     */
    public Command parseCommand(String userInput) {
        try {
            if (userInput.equals("bye")) {
                return new ExitCommand();
            } else if (userInput.equals("list")) {
                return new ListCommand();
            } else if (hasNumberPattern.matcher(userInput.split(" ")[0]).find()) {
                return parseHasNumberPatternCommand(userInput);
            } else if (taskPattern.matcher(userInput).find()) {
                return new AddCommand(userInput);
            } else if (hasKeywordPattern.matcher(userInput.split(" ")[0]).find()) {
                return new FindCommand(userInput);
            } else {
                throw new NotACommandException();
            }
        } catch (NotACommandException | EmptyInputException exception) {
            return new InvalidCommand(exception.toString());
        }
    }

    private Command parseHasNumberPatternCommand(String userInput) throws EmptyInputException {
        if (validUnmarkPattern.matcher(userInput).find()) {
            return new UnmarkCommand(userInput);
        } else if (validMarkPattern.matcher(userInput).find()) {
            return new MarkCommand(userInput);
        } else if (validDeletePattern.matcher(userInput).find()) {
            return new DeleteCommand(userInput);
        } else {
            throw new EmptyInputException(userInput.split(" ")[0], "number");
        }
    }

    /**
     * Creates a task from a user input string.
     * The task can be of type "to-do", "deadline", or "event".
     *
     * @param userInput the input string representing the task to be created.
     * @return the created task.
     * @throws EmptyInputException if the input is missing a description of the task,
     *     keywords, dates or times
     * @throws InvalidInputException if the input's format is invalid.
     */
    public Task createTask(String userInput) throws EmptyInputException, InvalidInputException {
        String[] arr = userInput.trim().split(" ", 2);
        String taskType = arr[0];
        if (arr.length == 1 || arr[1].trim().charAt(0) == '/') {
            throw new EmptyInputException(taskType, "description");
        }
        String string = arr[1];
        return switch (taskType) {
        case "deadline" -> Deadlines.of(string, this);
        case "event" -> Events.of(string, this);
        case "todo" -> ToDos.of(string);
        default -> null;
        };
    }

    /**
     * Parses tasks from a file and designates them to the task manager.
     *
     * @param path the path of the file to parse.
     * @param taskManager the task manager to add the parsed tasks to.
     * @throws NotACommandException if a task type is invalid.
     * @throws IOException if there is an issue reading the file.
     * @throws EmptyInputException if a task contains empty input.
     * @throws InvalidInputException if a task contains invalid input.
     */
    public void parseFromFile(Path path, TaskManager taskManager)
            throws NotACommandException, IOException, EmptyInputException, InvalidInputException {
        Scanner s = new Scanner(path);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] lineArr = line.split(" ", 2);
            String taskString = lineArr[1];
            Task task = createTask(taskString);
            String isDone = lineArr[0];
            addTaskFromFile(task, taskManager, isDone);
        }
    }

    private void addTaskFromFile(Task task, TaskManager taskManager, String isDone)
            throws NotACommandException {
        if (task == null) {
            throw new NotACommandException();
        }
        taskManager.addToList(task);
        if (isDone.equals("1")) {
            task.markDone();
        } else if (!isDone.equals("0")) {
            throw new NotACommandException();
        }
    }

    /**
     * Parses a date or time string and formats it into a consistent format.
     * Supports both date-first and time-first formats, and handles invalid inputs.
     *
     * @param string the input string representing the date or time.
     * @return the formatted date/time string.
     * @throws InvalidInputException if the input string is in an invalid format.
     */
    public String parseTime(String string) throws InvalidInputException {
        try {
            if (dateFirstPattern.matcher(string).find()) {
                return parseDateFirstPattern(string);
            } else if (timeFirstPattern.matcher(string).find()) {
                return parseTimeFirstPattern(string);
            }
            return string;
        } catch (DateTimeParseException e) {
            throw new InvalidInputException();
        }
    }

    private String parseDateFirstPattern(String string) throws DateTimeParseException {
        String[] arr = string.split(" ");
        LocalDate date = LocalDate.parse(arr[0]);
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (arr.length != 2) {
            return dateString;
        }
        String time = arr[1];
        if (Pattern.compile("[0-9]{2}:[0-9]{2}").matcher(time).find()) {
            DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter intendedFormat = DateTimeFormatter.ofPattern("hh:mm a");
            LocalTime originalTime = LocalTime.parse(time, originalFormat);
            return dateString + ", " + originalTime.format(intendedFormat);
        }
        return dateString + ", " + time;
    }

    private String parseTimeFirstPattern(String string) throws DateTimeParseException {
        String[] arr = string.split(" ");
        DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter intendedFormat = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime originalTime = LocalTime.parse(arr[0], originalFormat);
        String timeString = originalTime.format(intendedFormat);
        if (arr.length != 2) {
            return timeString;
        }
        String dateString = arr[1];
        if (Pattern.compile("[0-9]{4}(-[0-9]{2}){2}").matcher(dateString).find()) {
            LocalDate date = LocalDate.parse(dateString);
            dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return dateString + ", " + timeString ;
        }
        return dateString + ", " + timeString;
    }
}
