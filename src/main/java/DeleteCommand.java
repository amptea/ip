import java.util.ArrayList;

public class DeleteCommand extends Command {
    protected String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(ArrayList<Task> taskList) throws ChoiceBotException {
        try {
            if (description == null || description.isBlank()) {
                throw new ChoiceBotException("Please provide a task number to delete.");
            }
            if (taskList.isEmpty()) {
                throw new ChoiceBotException("No tasks available to delete.");
            }
            int taskNumber = Integer.parseInt(description.trim()) - 1;
            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new ChoiceBotException(String.format(
                        "Invalid task number. Please input a task number from 1 to %d",
                        taskList.size()));
            }
            Task task = taskList.get(taskNumber);
            task.deleteTask();
            taskList.remove(taskNumber);
        } catch (NumberFormatException e) {
            throw new ChoiceBotException("Sorry! Task number must be an integer.");
        }
    }
}
