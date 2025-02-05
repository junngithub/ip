package Essentials;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

import Commands.AddCommand;
import Commands.Command;
import Commands.DeleteCommand;
import Commands.ExitCommand;
import Commands.ListCommand;
import Commands.MarkCommand;
import Commands.UnmarkCommand;
import Exceptions.EmptyInputException;
import Exceptions.InvalidInputException;
import Exceptions.NotACommandException;
import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;

public class Parser {
    private final Pattern hasNumberPattern = Pattern.compile("^(mark|unmark|delete)$");
    private final Pattern validMarkPattern = Pattern.compile("^mark [1-9][0-9]*$");
    private final Pattern validUnmarkPattern = Pattern.compile("^unmark [1-9][0-9]*$");
    private final Pattern validDeletePattern = Pattern.compile("^delete [1-9][0-9]*$");
    private final Pattern taskPattern = Pattern.compile("^(todo|deadline|event)");
    private final Pattern dateFirstPattern = Pattern.compile("^[0-9]{4}(-[0-9]{2}){2}( [0-9]{2}:[0-9]{2})*$");
    private final Pattern timeFirstPattern = Pattern.compile("^[0-9]{2}:[0-9]{2}( [0-9]{4}(-[0-9]{2}){2})*$");

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

    public void parseFromFile (Path path, TaskManager taskManager)
            throws NotACommandException, IOException, EmptyInputException, InvalidInputException {
        Scanner s = new Scanner(path);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] lineArr = line.split(" ", 2);
            String taskString = lineArr[1];
            Task task = createTask(taskString);
            if (task == null) {
                throw new NotACommandException();
            }
            String done = lineArr[0];
            taskManager.addToList(task, false);
            if (done.equals("1")) {
                task.markDone();
            } else if (!done.equals("0")) {
                throw new NotACommandException();
            }
        }
    }

    public String parseTime(String string) throws InvalidInputException {
        try {
            if (dateFirstPattern.matcher(string).find()) {
                String[] arr = string.split(" ");
                LocalDate date = LocalDate.parse(arr[0]);
                String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                if (arr.length == 2) {
                    String time = arr[1];
                    if (Pattern.compile("[0-9]{2}:[0-9]{2}").matcher(time).find()) {
                        DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("HH:mm");
                        DateTimeFormatter intendedFormat = DateTimeFormatter.ofPattern("hh:mm a");
                        LocalTime originalTime = LocalTime.parse(time, originalFormat);
                        return dateString + ", " + originalTime.format(intendedFormat);
                    }
                    return dateString + ", " + time;
                }
                return dateString;
            } else if (timeFirstPattern.matcher(string).find()) {
                String[] arr = string.split(" ");
                DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("HH:mm");
                DateTimeFormatter intendedFormat = DateTimeFormatter.ofPattern("hh:mm a");
                LocalTime originalTime = LocalTime.parse(arr[0], originalFormat);
                String timeString = originalTime.format(intendedFormat);
                if (arr.length == 2) {
                    String dateString = arr[1];
                    if (Pattern.compile("[0-9]{4}(-[0-9]{2}){2}").matcher(dateString).find()) {
                        LocalDate date = LocalDate.parse(dateString);
                        dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                        return timeString + ", " + dateString;
                    }
                    return dateString + ", " + timeString;
                }
                return timeString;
            }
            return string;
        } catch (DateTimeParseException e) {
            throw new InvalidInputException();
        }

    }

}
