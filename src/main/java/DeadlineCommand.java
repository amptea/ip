import java.awt.*;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    protected String description;

    public DeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(ArrayList<Task> taskList) throws ChoiceBotException {
        if (!description.contains("/by ")) {
            throw new ChoiceBotException("Please follow format: deadline {description} /by {deadline}.");
        }
        String dueDate = description.split("/by ")[1].trim();
        String deadlineName = description.split("/by ")[0].trim();
        if (deadlineName.isBlank() || dueDate.isBlank()) {
            throw new ChoiceBotException("Please follow format: deadline {description} /by {deadline}.");
        }
        Task deadline = new Deadline(deadlineName, false, dueDate);
        taskList.add(deadline);
        Storage.saveFile(taskList);
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" + deadline);
        deadline.displayCount();
    }
}
