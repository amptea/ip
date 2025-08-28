package choicebot.ui;

import choicebot.ChoiceBotException;
import choicebot.task.Task;

import java.util.ArrayList;

public class UI {
    final String BOT_NAME = "choicebot.ChoiceBot";
    public static void showDottedLine() {
        System.out.println("---------------------------");
    }

    public void say(String name, String content) {
        System.out.println(name + ": " + content);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void welcome() {
        say(BOT_NAME, ": Hello, Welcome to choicebot.ChoiceBot!");
        say(BOT_NAME, ": What would you like to do?");
        showDottedLine();
    }

    public static void deleteTaskMessage(Task task) {
        System.out.println("Noted. I have removed the following task:");
        System.out.println("\t" + task);
        showDottedLine();
    }

    public static void displayCountMessage(Integer count) {
        if (count == 1) {
            System.out.println("Now you have " + count + " task in the list.");

        } else {
            System.out.println("Now you have " + count + " tasks in the list.");
        }
    }

    public void displayError(ChoiceBotException e) {
        displayMessage(e.getMessage());
        showDottedLine();
    }

    public void addTaskMessage(Task task) {
        displayMessage("Got it. I've added this task: ");
        displayMessage("\t" + task);
        task.displayCount();
        showDottedLine();
    }

    public void unmarkTaskMessage(Task task) {
        displayMessage("\tOk, I've unmarked the following task for you: ");
        displayMessage("\t" + task);
        showDottedLine();
    }

    public void markTaskMessage(Task task) {
        displayMessage("\tNice! I've marked this task as done: ");
        displayMessage("\t" + task);
        showDottedLine();
    }

    public void displayList(ArrayList<Task> taskList) {
        displayMessage("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            displayMessage("\t" + (i + 1) + "." + currentTask);
        }
        showDottedLine();
    }

    public void exitMessage() {
        say("choicebot.ChoiceBot", ": Thanks for stopping by! See you again!");
    }
}
