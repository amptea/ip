package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.task.TaskList;
import choicebot.ui.UI;

public class ListCommand extends Command {
    protected String description;

    public ListCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, UI ui) throws ChoiceBotException {
        if (!description.isBlank()) {
            throw new ChoiceBotException("Please only type the command \"list\"");
        }
        ui.displayList(tasks.getTaskList());
    }
}
