package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Represents a command that deletes a task from tasklist.
 */
public class DeleteCommand extends Command {
    protected String description;

    /**
     * Constructs a DeleteCommand with the given task index.
     *
     * @param description Index of task to be deleted from task list.
     */
    public DeleteCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the delete command by deleting specified task number from task list.
     * The task instance is also deleted from storage.
     * Displays a confirmation message through given UI.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @throws ChoiceBotException If index of task is not found in task list, or if description is blank.
     */
    @Override
    public void execute(TaskList tasks, UI ui) throws ChoiceBotException {
        try {
            if (description == null || description.isBlank()) {
                throw new ChoiceBotException("Please provide a task number to delete.");
            }
            if (tasks.isEmpty()) {
                throw new ChoiceBotException("No tasks available to delete.");
            }
            int taskNumber = Integer.parseInt(description.trim()) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new ChoiceBotException(String.format(
                        "Invalid task number. Please input a task number from 1 to %d",
                        tasks.size()));
            }
            Task task = tasks.getTask(taskNumber);
            task.deleteMessage();
            tasks.deleteTask(task);
            Storage.saveFile(tasks);
        } catch (NumberFormatException e) {
            throw new ChoiceBotException("Sorry! choicebot.task.Task number must be an integer.");
        }
    }
}
