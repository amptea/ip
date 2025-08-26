import java.util.ArrayList;

public class MarkCommand extends Command {
    protected String description;

    public MarkCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(ArrayList<Task> taskList) throws ChoiceBotException {
        try {
            if (description == null || description.isBlank()) {
                throw new ChoiceBotException("Please provide a task number to mark.");
            }
            if (taskList.isEmpty()) {
                throw new ChoiceBotException("No tasks available to mark.");
            }
            int taskNumber = Integer.parseInt(description.trim()) - 1;
            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new ChoiceBotException(String.format(
                        "Invalid task number. Please input a task number from 1 to %d",
                        taskList.size()));
            }
            Task task = taskList.get(taskNumber);
            ChoiceBot.markTask(task);
        } catch (NumberFormatException e) {
            throw new ChoiceBotException("Sorry! Task number must be an integer.");
        }
    }
}
