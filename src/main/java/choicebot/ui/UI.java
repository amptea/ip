package choicebot.ui;

import choicebot.ChoiceBotException;
import choicebot.task.Task;

import java.util.ArrayList;

/**
 * Handles all user interactions with ChoiceBot.
 * Provides methods for displaying messages, errors, tasks lists and feedback to the user.
 */
public class UI {
    /** Name used by ChoiceBot in its messages. */
    final String BOT_NAME = "ChoiceBot";

    /**
     * Displays a dotted line separator for easier readibility in console output.
     */
    public static void showDottedLine() {
        System.out.println("---------------------------");
    }

    /**
     * Displays a message with the given username.
     *
     * @param name The person name to be displayed in front of text.
     * @param content The content of the message to be displayed.
     */
    public void say(String name, String content) {
        System.out.println(name + ": " + content);
    }

    /**
     * Displays a message to the user.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Greets the user with a welcome message when ChoiceBot first initiates.
     */
    public void welcome() {
        say(BOT_NAME, ": Hello, Welcome to choicebot.ChoiceBot!");
        say(BOT_NAME, ": What would you like to do?");
        showDottedLine();
    }

    /**
     * Displays a message after deleting a task.
     */
    public static void deleteTaskMessage(Task task) {
        System.out.println("Noted. I have removed the following task:");
        System.out.println("\t" + task);
        showDottedLine();
    }

    /**
     * Displays the current number of tasks in the task list.
     * @param count The number of tasks in the task list.
     */
    public static void displayCountMessage(Integer count) {
        if (count == 1) {
            System.out.println("Now you have " + count + " task in the list.");

        } else {
            System.out.println("Now you have " + count + " tasks in the list.");
        }
    }

    /**
     * Displays an error message when a ChoiceBotException occurs.
     *
     * @param e The ChoiceBotException to display.
     */
    public void displayError(ChoiceBotException e) {
        displayMessage(e.getMessage());
        showDottedLine();
    }

    /**
     * Displays a message after adding a task.
     */
    public void addTaskMessage(Task task) {
        displayMessage("Got it. I've added this task: ");
        displayMessage("\t" + task);
        task.displayCount();
        showDottedLine();
    }

    /**
     * Displays a message after unmarking a task.
     */
    public void unmarkTaskMessage(Task task) {
        displayMessage("\tOk, I've unmarked the following task for you: ");
        displayMessage("\t" + task);
        showDottedLine();
    }

    /**
     * Displays a message after marking a task.
     */
    public void markTaskMessage(Task task) {
        displayMessage("\tNice! I've marked this task as done: ");
        displayMessage("\t" + task);
        showDottedLine();
    }

    /**
     * Displays the full list of task in order, with the number labelled before each task.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void displayList(ArrayList<Task> taskList) {
        displayMessage("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            displayMessage("\t" + (i + 1) + "." + currentTask);
        }
        showDottedLine();
    }

    /**
     * Displays the exit message when the conversation with ChoiceBot ends.
     */
    public void exitMessage() {
        say("choicebot.ChoiceBot", ": Thanks for stopping by! See you again!");
    }
}
