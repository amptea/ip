import java.util.ArrayList;

public class TodoCommand extends Command {
    protected String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, UI ui) throws ChoiceBotException {
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("Please add a description for toDo event.");
        }
        Task todo = new Todo(description, false);
        tasks.addTask(todo);
        Storage.saveFile(tasks);
        ui.addTaskMessage(todo);
    }
}

