package Essentials;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import Commands.AddCommand;
import Exceptions.EmptyInputException;
import Exceptions.NotACommandException;
import Tasks.Task;

public class Storage {
    private Path path;
    public Storage() {
        String home = System.getProperty("user.home");
        this.path = Path.of(home, "iP", "data", "icarus.txt");
    }

    public void loadSave(TaskManager taskManager) {
        boolean fileExists = java.nio.file.Files.exists(path);
        try {
            if (fileExists) {
                Scanner s = new Scanner(path);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    String[] lineArr = line.split(" ", 2);
                    String taskString = lineArr[1];
                    Task task = AddCommand.createTask(taskString);
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
            } else {
                String home = System.getProperty("user.home");
                Path directory = Files.createDirectories(Path.of(home, "iP", "data"));
                File f = new File(directory + "\\icarus.txt");
                f.createNewFile();
            }
        } catch (EmptyInputException | NotACommandException e) {
            System.out.println("file corrupted");
        } catch (IOException e) {
            System.out.println("something went wrong :(");
        }
    }

    public void updateSave(ArrayList<Task> list) throws IOException {

        FileWriter fw = new FileWriter(path.toString(), false);
        for (Task task : list) {
            fw.write(task.toFile());
        }
        fw.close();
    }


}
