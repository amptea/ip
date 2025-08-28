package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.ui.UI;

public class UnmarkCommand extends Command {
    protected String description;

    public UnmarkCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, UI ui) throws ChoiceBotException {
        try {
            if (description == null || description.isBlank()) {
                throw new ChoiceBotException("Please provide a task number to unmark.");
            }
            if (tasks.isEmpty()) {
                throw new ChoiceBotException("No tasks available to unmark.");
            }
            int taskNumber = Integer.parseInt(description.trim()) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new ChoiceBotException(String.format(
                        "Invalid task number. Please input a task number from 1 to %d",
                        tasks.size()));
            }
            Task task = tasks.getTask(taskNumber);
            task.markAsUndone();
            ui.unmarkTaskMessage(task);
            Storage.saveFile(tasks);
        } catch (NumberFormatException e) {
            throw new ChoiceBotException("Sorry! choicebot.task.Task number must be an integer.");
        }
    }
}
