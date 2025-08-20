import java.awt.desktop.SystemSleepEvent;
import java.lang.management.BufferPoolMXBean;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

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

            try {
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
                } else if (command.startsWith("delete")) {
                    int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= taskList.size()) {
                        throw new ChoiceBotException("Invalid task number. Please try again.");
                    }
                    Task task = taskList.get(taskNumber);
                    task.deleteTask();
                    taskList.remove(taskNumber);
                } else if (command.startsWith("todo") || command.startsWith("event") || command.startsWith("deadline")){
                    String[] commandParts = command.split(" ", 2);
                    String taskType = commandParts[0];
                    String taskInfo = commandParts.length > 1 ? commandParts[1] : "";
                    switch (taskType) {
                        case ("todo"):
                            Task todo = new Todo(taskInfo);
                            taskList.add(todo);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println("\t" + todo);
                            todo.displayCount();
                            break;
                        case ("deadline"):
                            if (!taskInfo.contains("/by ")) {
                                throw new ChoiceBotException("You must add a due date for Deadline tasks. Please try again.");
                            }
                            String dueDate = taskInfo.split("/by ")[1];
                            String deadlineName = taskInfo.split("/by ")[0];
                            Task deadline = new Deadline(deadlineName, dueDate);
                            taskList.add(deadline);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println("\t" + deadline);
                            deadline.displayCount();
                            break;
                        case ("event"):
                            if (!taskInfo.contains("/from ")) {
                                throw new ChoiceBotException("You must add a start date/time for Event tasks. Please try again.");
                            }
                            if (!taskInfo.contains("/to ")) {
                                throw new ChoiceBotException("You must add a end date/time for Event tasks. Please try again.");
                            }
                            String toFrom = taskInfo.split("/from ")[1];
                            String to = toFrom.split("/to ")[1];
                            String from = toFrom.split("/to ")[0];
                            String eventName = taskInfo.split("/from ")[0];
                            Task event = new Event(eventName, from, to);
                            taskList.add(event);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println("\t" + event);
                            event.displayCount();
                            break;
                    }
                } else {
                    throw new Exception("Invalid command entered. Please try again.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
