package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.ui.UI;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that contain a given keyword in the task description.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the given search keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * Executes the find command by filtering tasks that contain the given keyword.
     * Displays all filtered tasks to the user using the given UI.
     * Displays no task found message if no matching tasks available.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @throws ChoiceBotException If the keyword is empty.
     */
    @Override
    public void execute(TaskList tasks, UI ui) throws ChoiceBotException {
        if (keyword.isBlank()) {
            throw new ChoiceBotException("Please provide a keyword to search with.");
        }

        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (!matchingTasks.isEmpty()) {
            ui.displayMatchingTasks(matchingTasks);
        } else {
            ui.displayNoMatchingTasks();
        }
    }

}
