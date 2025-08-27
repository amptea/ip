import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected static String filePath = "";

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    public ArrayList<Task> loadFile() throws ChoiceBotException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return taskList;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                Task task = Task.loadTask(text);
                taskList.add(task);
            }
        } catch (IOException e) {
            throw new ChoiceBotException("Could not read tasks from file: " + e.getMessage());
        }
        return taskList;
    }

    public static void saveFile(ArrayList<Task> tasks) throws ChoiceBotException {
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }

            try (FileWriter fw = new FileWriter(file)) {
                for (Task t : tasks) {
                    fw.write(t.saveTask() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new ChoiceBotException("File not saved successfully: " + e.getMessage());
        }
    }
}
