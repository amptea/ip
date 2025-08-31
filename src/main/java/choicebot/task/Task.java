package choicebot.task;

import choicebot.ChoiceBotException;
import choicebot.ui.UI;

import java.time.LocalDate;

/**
 * Represents the task superclass.
 * <p>
 * A task requires only a description.
 * </p>
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected static int count;
    protected String type;

    /**
     * Constructs a new task with the given description and completion status.
     * @throws ChoiceBotException If the description is null or blank.
     */
    public Task(String description, Boolean isDone) throws ChoiceBotException {
        this.description = description;
        this.isDone = isDone;
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("You must add a description for toDo tasks. Please try again.");
        } else {
            count++;
        }
    }

    /**
     * Returns X if task is marked as done, and " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks boolean isDone as true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks boolean isDone as false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Displays the number of tasks currently in the task list.
     */
    public void displayCount() {
        UI.displayCountMessage(count);
    }

    public static int getCount() {
        return count;
    }

    /**
     * Displays the number of tasks remaining in task list after deleting current task.
     */
    public void deleteMessage() {
        UI.deleteTaskMessage(this);
        count--;
        displayCount();
    }

    public String getType() {
        return this.type;
    }

    /**
     * Saves the current task object in a specified String format.
     * The string is to be saved in the storage file.
     */
    public String saveTask() {
        return String.format("%s | %d | %s",
                this.getType(),
                this.isDone ? 1 : 0,
                this.description);
    }

    /**
     * Instantiates a new task object from the saved String format retrieved from storage file.
     * @param savedText String of task saved in the storage file.
     * @return new Task object.
     * @throws ChoiceBotException If savedText is in the wrong format.
     */
    public static Task loadTask(String savedText) throws ChoiceBotException {
        String[] taskParts = savedText.split("\\| ");
        String type = taskParts[0].trim();
        boolean isDone = taskParts[1].trim().equals("1");
        String description = taskParts[2].trim();

        switch (type) {
        case ("T"):
            return new Todo(description, isDone);
        case("D"):
            String dueDateString = taskParts[3].trim();
            LocalDate dueDate = LocalDate.parse(dueDateString);
            return new Deadline(description, isDone, dueDate);
        case ("E"):
            String startDate = taskParts[3].trim();
            String endDate = taskParts[4].trim();
            return new Event(description, isDone, startDate, endDate);
        default:
            throw new ChoiceBotException("Task could not be loaded properly.");
        }
    }

    /**
     * Returns the description of task.
     */
    public String getDescription() {
        return this.description;
    }
}
