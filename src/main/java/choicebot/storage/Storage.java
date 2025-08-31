package choicebot.storage;

import choicebot.ChoiceBotException;
import choicebot.task.Task;
import choicebot.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles reading and writing tasks from a local file to the program.
 * Tasks are stored line by line, with each line being a formatted representation of the Task.
 */
public class Storage {
    /** The path to the file used for saving and loading tasks. */
    protected static String filePath = "";

    /**
     * Creates a new Storage instance based on the given file path.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file into a Tasklist.
     * Returns an empty TaskList if the file does not exist.
     *
     * @return Tasklist of tasks loaded from file.
     * @throws ChoiceBotException If the file could not be read.
     */
    public TaskList loadFile() throws ChoiceBotException {
        TaskList taskList = new TaskList();
        File file = new File(filePath);
        if (!file.exists()) {
            return taskList;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                Task task = Task.loadTask(text);
                taskList.addTask(task);
            }
        } catch (IOException e) {
            throw new ChoiceBotException("Could not read tasks from file: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Saves the given Tasklist to the storage file in formatted representation.
     * <p>
     * Creates the parent directory if it does not exist.
     * </p>
     *
     * @throws ChoiceBotException If the file cannot be written to.
     */
    public static void saveFile(TaskList tasks) throws ChoiceBotException {
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }

            try (FileWriter fw = new FileWriter(file)) {
                ArrayList<Task> taskList = tasks.getTaskList();
                for (Task t : taskList) {
                    fw.write(t.saveTask() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new ChoiceBotException("File not saved successfully: " + e.getMessage());
        }
    }
}
