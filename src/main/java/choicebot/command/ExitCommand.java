package choicebot.command;

import choicebot.task.TaskList;
import choicebot.ui.UI;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, UI ui) {
        ui.exitMessage();
    }
}
