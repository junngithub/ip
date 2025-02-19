package essentials;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.SetCommand;
import commands.ShowSyntaxCommand;
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
    private HashMap<String, String> syntaxMap;
    private Pattern validFindPattern = Pattern.compile("^find");
    private Pattern validListPattern = Pattern.compile("^list$");
    private Pattern validMarkPattern = Pattern.compile("^mark [1-9][0-9]*$");
    private Pattern validUnmarkPattern = Pattern.compile("^unmark [1-9][0-9]*$");
    private Pattern validDeletePattern = Pattern.compile("^delete [1-9][0-9]*$");
    private Pattern hasNumberPattern = Pattern.compile("^(mark|unmark|delete)$");
    private Pattern taskPattern = Pattern.compile("^(todo|deadline|event)$");
    private Pattern hasKeywordPattern = Pattern.compile("^(find|set)$");
    private final Pattern validSetPattern =
            Pattern.compile("^set (todo|deadline|event|list|find|unmark|mark|delete)");
    private final Pattern validTimePattern = Pattern.compile("[0-9]{2}:[0-9]{2}");
    private final Pattern validDatePattern = Pattern.compile("[0-9]{4}(-[0-9]{2}){2}");
    private final Pattern dateFirstPattern = Pattern.compile("^[0-9]{4}(-[0-9]{2}){2}( [0-9]{2}:[0-9]{2})*$");
    private final Pattern timeFirstPattern = Pattern.compile("^[0-9]{2}:[0-9]{2}( [0-9]{4}(-[0-9]{2}){2})*$");

    /**
     * Constructs a Parser instance.
     */
    public Parser() {
        this.syntaxMap = new HashMap<>();
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
            } else if (userInput.equals("syntax")) {
                return new ShowSyntaxCommand();
            } else if (validListPattern.matcher(userInput).find()) {
                return new ListCommand();
            } else if (hasNumberPattern.matcher(userInput.split(" ")[0]).find()) {
                return parseHasNumberPatternCommand(userInput);
            } else if (taskPattern.matcher(userInput.split(" ")[0]).find()) {
                return new AddCommand(userInput);
            } else if (hasKeywordPattern.matcher(userInput.split(" ")[0]).find()) {
                return parseHasKeywordCommand(userInput);
            } else {
                throw new NotACommandException();
            }
        } catch (NotACommandException | EmptyInputException | InvalidInputException exception) {
            return new InvalidCommand(exception.toString());
        }
    }

    private Command parseHasKeywordCommand(String userInput) throws InvalidInputException {
        if (validFindPattern.matcher(userInput).find()) {
            return new FindCommand(userInput);
        }
        if (validSetPattern.matcher(userInput).find()) {
            return new SetCommand(userInput);
        } else {
            throw new InvalidInputException(userInput.split(" ")[1], false);
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
    public Task createTask(String userInput, boolean isFromFile) throws EmptyInputException, InvalidInputException {
        String[] arr = userInput.trim().split(" ", 2);
        String commandString = arr[0];
        String taskType = parseTaskType(commandString, isFromFile);
        assert taskType != null;
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

    private String parseTaskType(String commandString, boolean isFromFile) {
        if (isFromFile) {
            return commandString;
        }
        if (syntaxMap.get("todo").equals(commandString)) {
            return "todo";
        } else if (syntaxMap.get("deadline").equals(commandString)) {
            return "deadline";
        } else if (syntaxMap.get("event").equals(commandString)) {
            return "event";
        } else {
            return null;
        }
    }

    /**
     * TODO
     * @return TODO
     */
    public String saySyntax() {
        Set<Map.Entry<String, String>> entries = this.syntaxMap.entrySet();
        StringBuilder syntax = new StringBuilder();
        for (Map.Entry<String, String> entry: entries) {
            syntax.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return syntax.toString();
    }

    /**
     * Parses tasks from a file and designates them to the task manager.
     *
     * @param file the path of the file to parse.
     * @param taskManager the task manager to add the parsed tasks to.
     * @throws NotACommandException if a task type is invalid.
     * @throws IOException if there is an issue reading the file.
     * @throws EmptyInputException if a task contains empty input.
     * @throws InvalidInputException if a task contains invalid input.
     */
    public void parseFromFile(File file, TaskManager taskManager)
            throws NotACommandException, IOException, EmptyInputException, InvalidInputException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] lineArr = line.split(" ", 2);
            String taskString = lineArr[1];
            Task task = createTask(taskString, true);
            String isDone = lineArr[0];
            addTaskFromFile(task, taskManager, isDone);
        }
    }

    /**
     * TODO
     * @param file TODO
     * @throws IOException TODO
     * @throws InvalidInputException TODO
     */
    public void parseFromFile(File file) throws IOException, InvalidInputException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] lineArr = line.split(" ", 2);
            String keyword = lineArr[0];
            String preferredKeyword = lineArr[1];
            updateSyntax(keyword, preferredKeyword);
        }

    }

    /**
     * TODO
     * @param keyword TODO
     * @param preferredKeyword TODO
     * @throws InvalidInputException TODO
     */
    public void updateSyntax(String keyword, String preferredKeyword) throws InvalidInputException {
        // ensure no duplicates
        if (syntaxMap.containsValue(preferredKeyword)) {
            throw new InvalidInputException(preferredKeyword, true);
        }
        this.syntaxMap.put(keyword, preferredKeyword);
        if (keyword.equals(preferredKeyword)) {
            return;
        }
        switch (keyword) {
        case "todo", "deadline", "event" -> taskPattern = Pattern.compile("^(" + syntaxMap.get("todo") + "|"
                + syntaxMap.get("deadline") + "|" + syntaxMap.get("event") + ")$");
        case "find" -> {
            validFindPattern = Pattern.compile("^" + preferredKeyword);
            hasKeywordPattern = Pattern.compile("^(" + preferredKeyword + "|set)$");
        }
        case "list" -> validListPattern = Pattern.compile("^" + preferredKeyword + "$");
        case "delete" -> {
            updateHasNumberPattern();
            validDeletePattern = Pattern.compile("^" + preferredKeyword + " [1-9][0-9]*$");
        }
        case "mark" -> {
            updateHasNumberPattern();
            validMarkPattern = Pattern.compile("^" + preferredKeyword + " [1-9][0-9]*$");
        }
        case "unmark" -> {
            updateHasNumberPattern();
            validUnmarkPattern = Pattern.compile("^" + preferredKeyword + " [1-9][0-9]*$");
        }
        default -> {
            // should not reach this point i.e. no other keywords should be added or manipulated
            assert false;
        }
        }
    }

    private void updateHasNumberPattern() {
        hasNumberPattern = Pattern.compile("^(" + syntaxMap.get("mark") + "|" + syntaxMap.get("unmark")
                + "|" + syntaxMap.get("delete") + ")$");
    }

    private void addTaskFromFile(Task task, TaskManager taskManager, String isDone)
            throws NotACommandException, InvalidInputException {
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
        if (validTimePattern.matcher(time).find()) {
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
        if (validDatePattern.matcher(dateString).find()) {
            LocalDate date = LocalDate.parse(dateString);
            dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return dateString + ", " + timeString;
        }
        return dateString + ", " + timeString;
    }

    public HashMap<String, String> getSyntaxMap() {
        return this.syntaxMap;
    }
}
