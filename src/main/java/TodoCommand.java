import java.util.ArrayList;

public class TodoCommand extends Command {
    protected String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(ArrayList<Task> taskList) throws ChoiceBotException {
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("Please add a description for toDo event.");
        }
        Task todo = new Todo(description);
        taskList.add(todo);
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" + todo);
        todo.displayCount();
    }
}

