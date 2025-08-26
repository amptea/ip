import java.awt.*;
import java.util.ArrayList;

public class ListCommand extends Command {
    protected String description;

    public ListCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(ArrayList<Task> taskList) throws ChoiceBotException {
        if (!description.isBlank()) {
            throw new ChoiceBotException("Please only type the command \"list\"");
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println("\t" + (i + 1) + "." + currentTask);
        }
        ChoiceBot.addDottedLine();
    }
}
