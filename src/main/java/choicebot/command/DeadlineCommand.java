package choicebot.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.Deadline;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Represents a command that creates and adds a Deadline task to tasklist.
 * A deadline follows the format: deadline {description} /by {yyyy-mm-dd}
 */
public class DeadlineCommand extends Command {
    protected String description;

    /**
     * Constructs a DeadlineCommand with the given description.
     *
     * @param description Contains name of Deadline, and due date.
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the deadline command by creating new deadline instance.
     * The deadline instance is added to given task list and saved to storage.
     * Displays a confirmation message through given UI.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @throws ChoiceBotException If command does not have /by, or deadline name or due date is blank.
     */
    @Override
    public void execute(TaskList tasks, UI ui) throws ChoiceBotException {
        if (!description.contains("/by ")) {
            throw new ChoiceBotException(
                    "Please follow format: deadline {description} /by {deadline}.");
        }

        String dueDateString = description.split("/by ")[1].trim();
        String deadlineName = description.split("/by ")[0].trim();

        if (deadlineName.isBlank() || dueDateString.isBlank()) {
            throw new ChoiceBotException(
                    "Please follow format: deadline {description} /by {deadline}.");
        }

        try {
            LocalDate dueDate = LocalDate.parse(dueDateString);
            Task deadline = new Deadline(deadlineName, false, dueDate);
            tasks.addTask(deadline);
            Storage.saveFile(tasks);
            ui.addTaskMessage(deadline);
        } catch (DateTimeParseException e) {
            throw new ChoiceBotException("Please use format \"yyyy-mm-dd\" for deadline.");
        }
    }
}
