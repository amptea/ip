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
                    System.out.println("\t" + (i + 1) + "." + currentTask);
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
                String[] commandParts = command.split(" ", 2);
                String taskType = commandParts[0];
                String taskInfo = commandParts.length > 1 ? commandParts[1] : "";
                if (taskInfo.isEmpty()) {
                    System.out.println("Invalid input. Please try again");
                    continue;
                }
                if (taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event")) {
                    System.out.println("Got it. I've added this task: ");
                }
                switch (taskType) {
                    case ("todo"):
                        Task todo = new Todo(taskInfo);
                        taskList.add(todo);
                        System.out.println(todo);
                        todo.displayCount();
                        break;
                    case ("deadline"):
                        String dueDate = taskInfo.split("/by ")[1];
                        String deadlineName = taskInfo.split("/by ")[0];
                        Task deadline = new Deadline(deadlineName, dueDate);
                        taskList.add(deadline);
                        System.out.println(deadline);
                        deadline.displayCount();
                        break;
                    case ("event"):
                        String toFrom = taskInfo.split("/from ")[1];
                        String to = toFrom.split("/to ")[1];
                        String from = toFrom.split("/to ")[0];
                        String eventName = taskInfo.split("/from ")[0];
                        Task event = new Event(eventName, from, to);
                        taskList.add(event);
                        System.out.println(event);
                        event.displayCount();
                        break;
                    default:
                        System.out.println("Invalid task type. Please try again.");
                }

            }
        }
        System.out.println(BOT_NAME + ": Thanks for stopping by! See you again!");
    }

    public static void markTask(Task task) {
        task.markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + task);
        System.out.println("---------------------------");
    }

    public static void unmarkTask(Task task) {
        task.markAsUndone();
        System.out.println("\tOk, I've unmarked the following task for you: ");
        System.out.println("\t" + task);
        System.out.println("---------------------------");
    }
}
