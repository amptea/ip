import java.awt.desktop.SystemSleepEvent;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ChoiceBot {
    public static void main(String[] args) {
        new ChoiceBot("data/tasks.txt");
    }

    public ChoiceBot(String filePath) {
        Scanner sc = new Scanner(System.in);
        final String BOT_NAME = "ChoiceBot";
        final String USER_NAME = "User";
        boolean isEnded = false;
        ArrayList<Task> taskList = new ArrayList<>();
        Storage storage = new Storage(filePath);
        say(BOT_NAME, ": Hello, Welcome to ChoiceBot!");
        say(BOT_NAME, ": What would you like to do?");
        addDottedLine();

        try {
            taskList = storage.loadFile();
        } catch (ChoiceBotException e) {
            say("", e.getMessage());
        }
        while (!isEnded) {
            String input = sc.nextLine();

            try {
                Command command = Parser.parse(input);
                command.execute(taskList);
                if (command instanceof ExitCommand) {
                    isEnded = true;
                }
            } catch (ChoiceBotException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void markTask(Task task) {
        task.markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + task);
        addDottedLine();
    }

    public static void unmarkTask(Task task) {
        task.markAsUndone();
        System.out.println("\tOk, I've unmarked the following task for you: ");
        System.out.println("\t" + task);
        addDottedLine();
    }

    public static void addDottedLine() {
        System.out.println("---------------------------");
    }

    public static void say(String name, String content) {
        System.out.println(name + ": " + content);
    }
}
