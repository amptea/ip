import java.awt.desktop.SystemSleepEvent;
import java.lang.management.BufferPoolMXBean;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ChoiceBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String BOT_NAME = "ChoiceBot";
        final String USER_NAME = "User";
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(BOT_NAME + ": Hello, Welcome to ChoiceBot!");
        System.out.println(BOT_NAME + ": What would you like to do?");
        System.out.println("---------------------------");
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task currentTask = taskList.get(i);
                    String statusIcon = currentTask.getStatusIcon();
                    String taskDescription = currentTask.getDescription();
                    System.out.println("\t" + (i + 1) + ".[" + statusIcon + "] " + taskDescription);
                }
                System.out.println("---------------------------");
            } else if (command.startsWith("mark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
                Task task = taskList.get(taskNumber);
                markTask(task);
            } else if (command.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
                Task task = taskList.get(taskNumber);
                unmarkTask(task);
            } else {
                taskList.add(new Task(command));
                System.out.println("\tadded: " + command);
                System.out.println("---------------------------");
            }
        }
        System.out.println(BOT_NAME + ": Thanks for stopping by! See you again!");
    }

    public static void markTask(Task task) {
        task.markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        String statusIcon = task.getStatusIcon();
        String taskDescription = task.getDescription();
        System.out.println("\t\t[" + statusIcon + "] " + taskDescription);
        System.out.println("---------------------------");
    }

    public static void unmarkTask(Task task) {
        task.markAsUndone();
        System.out.println("\tOk, I've unmarked the following task for you: ");
        String statusIcon = task.getStatusIcon();
        String taskDescription = task.getDescription();
        System.out.println("\t\t[" + statusIcon + "] " + taskDescription);
        System.out.println("---------------------------");
    }
}
