package choicebot.task;

import choicebot.ChoiceBotException;
import choicebot.ui.UI;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int count;
    protected String type;

    public Task(String description, Boolean isDone) throws ChoiceBotException {
        this.description = description;
        this.isDone = isDone;
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("You must add a description for toDo tasks. Please try again.");
        } else {
            count++;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void displayCount() {
        UI.displayCountMessage(count);
    }

    public static int getCount() {
        return count;
    }

    public void deleteMessage() {
        UI.deleteTaskMessage(this);
        count--;
        displayCount();
    }

    public String getType() {
        return this.type;
    }

    public String saveTask() {
        return String.format("%s | %d | %s",
                this.getType(),
                this.isDone ? 1 : 0,
                this.description);
    }

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
