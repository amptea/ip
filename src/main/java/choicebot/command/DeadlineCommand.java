package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.Deadline;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.ui.UI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    protected String description;

    public DeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, UI ui) throws ChoiceBotException {
        if (!description.contains("/by ")) {
            throw new ChoiceBotException("Please follow format: deadline {description} /by {deadline}.");
        }
        String dueDateString = description.split("/by ")[1].trim();
        String deadlineName = description.split("/by ")[0].trim();
        if (deadlineName.isBlank() || dueDateString.isBlank()) {
            throw new ChoiceBotException("Please follow format: deadline {description} /by {deadline}.");
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
