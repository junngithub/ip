package essentials;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private Path tasksPath;
    private Path syntaxPath;

    /**
     * TODO
     */
    public Storage() {
        String home = System.getProperty("user.home");
        this.tasksPath = Path.of(home, "iP", "data", "tasks.txt");
        this.syntaxPath = Path.of(home, "iP", "data", "syntax.txt");
    }

    /**
     * Loads tasks from the save file into the TaskManager.
     * Creates the file and necessary directory if it doesn't exist.
     *
     * @param taskManager the TaskManager to load tasks into.
     * @param parser the parser used to read the file.
     */
    public void loadSavedTasks(TaskManager taskManager, Parser parser) {
        boolean fileExists = Files.exists(tasksPath);
        try {
            if (fileExists) {
                parser.parseFromFile(tasksPath, taskManager);
            } else {
                String home = System.getProperty("user.home");
                Path directory = Files.createDirectories(Path.of(home, "iP", "data"));
                File f = new File(directory + "\\tasks.txt");
                f.createNewFile();
            }
        } catch (EmptyInputException | NotACommandException | InvalidInputException e) {
            System.out.println("file corrupted");
        } catch (IOException e) {
            System.out.println("something went wrong :(");
        }
    }

    /**
     * TODO
     * @param parser TODO
     */
    public void loadSyntaxPreferences(Parser parser) {
        boolean fileExists = Files.exists(tasksPath);
        if (!fileExists) {
            System.out.println("missing directory");
        }
        try {
            parser.parseFromFile(syntaxPath);
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
        FileWriter fw = new FileWriter(tasksPath.toString(), false);
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
        FileWriter fw = new FileWriter(syntaxPath.toString(), false);
        for (Map.Entry<String, String> entry : entries) {
            fw.write(entry.getKey() + " " + entry.getValue() + "\n");
        }
        fw.close();
    }
}
