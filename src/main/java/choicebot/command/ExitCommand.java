package choicebot.command;

import choicebot.storage.Storage;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Represents a command that exits from the application.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    /**
     * Executes the chatbot, displaying a confirmation message through given UI.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @param storage Storage used in current instance.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.exitMessage();
    }
}
