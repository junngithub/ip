package essentials;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import exceptions.EmptyInputException;
import exceptions.InvalidInputException;
import exceptions.NotACommandException;
import tasks.Task;

/**
 * The Storage class is responsible for handling the loading and saving of task data
 * from and to a file. It provides functionality to load previously saved task data into
 * a TaskManager instance and to update the save file with the current task list.
 * The default save file is located at "{user.home}/iP/data/icarus.txt".
 */
public class Storage {
    private String tasksPath;
    private String syntaxPath;

    /**
     * TODO
     */
    public Storage() {
        this.tasksPath = "data/tasks.txt";
        this.syntaxPath = "data/syntax.txt";
    }

    /**
     * Loads tasks from the save file into the TaskManager.
     * Creates the file and necessary directory if it doesn't exist.
     *
     * @param taskManager the TaskManager to load tasks into.
     * @param parser the parser used to read the file.
     */
    public void loadSavedTasks(TaskManager taskManager, Parser parser) {
        File file = new File(tasksPath);
        boolean fileExists = file.exists();
        try {
            if (fileExists) {
                parser.parseFromFile(file, taskManager);
            } else {
                file.getParentFile().mkdirs();
                Files.createFile(Paths.get(tasksPath));
            }
        } catch (EmptyInputException | NotACommandException | InvalidInputException e) {
            System.out.println("file corrupted");
        } catch (IOException e) {
            System.out.println("something went wrong :(");
        }
    }

    public void loadDefaultSyntax() throws IOException {
        FileWriter fw = new FileWriter(syntaxPath);
        String defaults = """
                todo todo
                deadline deadline
                event event
                find find
                list list
                mark mark
                unmark unmark
                delete delete
                """;
        fw.write(defaults);
        fw.close();
    }

    /**
     * TODO
     * @param parser TODO
     */
    public void loadSyntaxPreferences(Parser parser) {
        File file = new File(syntaxPath);
        boolean fileExists = file.exists();
        try {
            if (!fileExists) {
                file.getParentFile().mkdirs();
                Files.createFile(Paths.get(syntaxPath));
                loadDefaultSyntax();
            }
            parser.parseFromFile(file);
        } catch (IOException | InvalidInputException e) {
            System.out.println("something went wrong :(");
        }
    }

    /**
     * Updates the save file with the current task list.
     *
     * @param list the list of tasks to save.
     * @throws IOException if an error occurs while saving the file.
     */
    public void updateTasks(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(tasksPath, false);
        for (Task task : list) {
            fw.write(task.toFile() + "\n");
        }
        fw.close();
    }

    /**
     * TODO
      * @param parser TODO
     * @throws IOException TODO
     */
    public void updateSyntaxPreferences(Parser parser) throws IOException {
        Set<Map.Entry<String, String>> entries = parser.getSyntaxMap().entrySet();
        FileWriter fw = new FileWriter(syntaxPath, false);
        for (Map.Entry<String, String> entry : entries) {
            fw.write(entry.getKey() + " " + entry.getValue() + "\n");
        }
        fw.close();
    }

}
