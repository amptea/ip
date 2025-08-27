import java.awt.*;
import java.util.ArrayList;

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
