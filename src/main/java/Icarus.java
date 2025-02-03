import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Pattern;

public class Icarus {
    private ArrayList<Task> list;
    private final Pattern hasNumberPattern = Pattern.compile("^(mark|unmark|delete)$");
    private final Pattern validMarkPattern = Pattern.compile("^mark [1-9][0-9]*$");
    private final Pattern validUnmarkPattern = Pattern.compile("^unmark [1-9][0-9]*$");
    private final Pattern validDeletePattern = Pattern.compile("^delete [1-9][0-9]*$");
    private final Pattern taskPattern = Pattern.compile("^(todo|deadline|event)");
    private final String border = "--------------------------------------------------";


    public Icarus()  {
        this.list = new ArrayList<>();
        greet();
        try {
            loadSave();
        } catch (Exception e) {
            System.out.println("something went wrong");
        }
        readCommand();
    }

    private void greet() {
        String greeting = border +
                """ 
                \nGreetings! My name is Icarus.
                How can I be of service to you today?
                """ + border;
        System.out.println(greeting);
    }

    private void sayBye() {
        try {
            updateSave();
        } catch (IOException e ){
            System.out.println("something went wrong: " + e);
            readCommand();
        }
        String bye = "Bye! See you next time, my friend.\n" + border;
        System.out.println(bye);
    }

    private void updateSave() throws IOException {
        String home = System.getProperty("user.home");
        Path path = Path.of(home, "iP", "data", "icarus.txt");
        FileWriter fw = new FileWriter(path.toString(), false);
        for (Task task : list) {
            fw.write(task.toFile());
        }
        fw.close();

    }

    private void loadSave() throws IOException {
        String home = System.getProperty("user.home");
        Path path = Path.of(home, "iP", "data", "icarus.txt");
        boolean fileExists = java.nio.file.Files.exists(path);
        if (fileExists) {
            Scanner s = new Scanner(path);
            while (s.hasNext()) {
                try {
                    String line = s.nextLine();
                    String[] lineArr = line.split(" ", 2);
                    String taskString = lineArr[1];
                    Task task = createTask(taskString);
                    if (task == null) {
                        throw new NotACommandException();
                    }
                    String done = lineArr[0];
                    addToList(task, false);
                    if (done.equals("1")) {
                        task.markDone();
                    } else if (!done.equals("0")) {
                        throw new NotACommandException();
                    }

                } catch (EmptyInputException | NotACommandException e) {
                    System.out.println("file corrupted");
                }
            }
        } else {
            java.nio.file.Path directory = Files.createDirectories(Path.of(home, "iP", "data"));
            File f = new File(directory + "\\icarus.txt");
            f.createNewFile();
        }

    }

    private void markDone(String userInput) throws InvalidNumberException {
        String[] arr = userInput.split(" ");
        int i = Integer.parseInt(arr[1]);
        int size = this.list.size();
        if (i > size) {
            throw new InvalidNumberException(i, size);
        }
        Task task = list.get(i - 1);
        task.markDone();
        String message = "Very well, I have marked this as completed: \n" + task;
        System.out.println(message);
        System.out.println(border);
        readCommand();
    }

    private void markUndone(String userInput) throws InvalidNumberException {
        String[] arr = userInput.split(" ");
        int i = Integer.parseInt(arr[1]);
        int size = this.list.size();
        if (i > size) {
            throw new InvalidNumberException(i, size);
        }
        Task task = list.get(i - 1);
        task.markUndone();
        String message = "Sure, I have marked this as unfinished: \n" + task;
        System.out.println(message);
        System.out.println(border);
        readCommand();
    }

    private void returnNumberOfItems() {
        System.out.println("You have " + list.size() + " item(s) in your list.");
    }

    private void delete(String userInput) throws InvalidNumberException {
        String[] arr = userInput.split(" ");
        int i = Integer.parseInt(arr[1]);
        int size = this.list.size();
        if (i > size) {
            throw new InvalidNumberException(i, size);
        }
        Task task = list.remove(i - 1);
        String message = "I have removed this item: \n" + task;
        System.out.println(message);
        System.out.println(border);
        readCommand();
    }

    private Task createTask(String userInput) throws EmptyInputException {
        String[] arr = userInput.split(" ", 2);
        String taskType = arr[0];
        if (arr.length == 1 || arr[1].trim().charAt(0) == '/') {
            throw new EmptyInputException(taskType, "description");
        }
        String string = arr[1];
        switch (taskType) {
            case "deadline":
                if (!string.contains("/by")) {
                    throw new EmptyInputException(taskType, "missing by");
                }
                String[] deadlineArr = string.split("/by");
                if (deadlineArr.length == 1 || deadlineArr[1].isBlank()) {
                    throw new EmptyInputException(taskType + " /by", "description");
                }
                return new Deadlines(deadlineArr[0].trim(), deadlineArr[1].trim());
            case "event":
                if (!string.contains("/from")) {
                    throw new EmptyInputException(taskType, "missing from");
                }
                String[] eventArr = string.split("/from");
                if (eventArr.length == 1 || eventArr[1].isBlank()) {
                    throw new EmptyInputException(taskType + " /from", "description");
                }
                String task = eventArr[0].trim();
                String leftover = eventArr[1];
                if (!leftover.contains("/to")) {
                    throw new EmptyInputException(taskType, "missing to");
                }
                String[] stringArr = leftover.split("/to");
                if (stringArr.length == 1 || stringArr[1].isBlank()) {
                    throw new EmptyInputException(taskType + " /to", "description");
                }
                return new Events(task, stringArr[0].trim(), stringArr[1].trim());
            case "todo":
                return new ToDos(string);
        }
        return null;
    }

    private void addToList(Task task, boolean toCall) {
        list.add(task);
        if (toCall) {
            System.out.println("I have added: \n" + task.toString());
            System.out.println(border);
            readCommand();
        }
    }

    private void returnList() {
        if (list.isEmpty()) {
            System.out.println("You have no items in your list.");
        } else {
            System.out.println("Here is your list:");
            int i = 1;
            for (Task item : list) {
                String str = i + ". " + item.toString();
                System.out.println(str);
                i++;
            }
        }
        System.out.println(border);
        readCommand();
    }

    private void readCommand() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println(border);
        try {
            if (userInput.equals("bye")) {
                sayBye();
            } else if (userInput.equals("list")) {
                returnList();
            } else if (hasNumberPattern.matcher(userInput.split(" ")[0]).find()) {
                if (validUnmarkPattern.matcher(userInput).find()) {
                    markUndone(userInput);
                } else if (validMarkPattern.matcher(userInput).find()) {
                    markDone(userInput);
                } else if (validDeletePattern.matcher(userInput).find()) {
                    delete(userInput);
                    returnNumberOfItems();
                } else {
                    throw new EmptyInputException(userInput.split(" ")[0], "number");
                }

            } else if (taskPattern.matcher(userInput).find()) {
                Task task = createTask(userInput);
                addToList(task, true);
                returnNumberOfItems();
            } else {
                throw new NotACommandException();
            }
        } catch (NotACommandException | EmptyInputException | InvalidNumberException exception) {
            System.out.println(exception);
            System.out.println(border);
            readCommand();
        }

    }

    private void echo(String userInput) {
        System.out.println(userInput + "\n");
    }

    public static void main(String[] args) {
        Icarus chatbot = new Icarus();
    }
}
