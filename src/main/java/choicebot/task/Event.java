package choicebot.task;

import choicebot.ChoiceBotException;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, Boolean isDone, String from, String to) throws ChoiceBotException {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.type = "E";
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("You must add a description for choicebot.task.Event tasks. Please try again.");
        }
        if (from == null) {
            throw new ChoiceBotException("You must add a valid start date/time for choicebot.task.Event tasks. Please try again.");
        }
        if (to == null) {
            throw new ChoiceBotException("You must add a valid end date/time for choicebot.task.Event tasks. Please try again.");
        } else {
            count++;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String saveTask() {
        return String.format("%s | %d | %s | %s | %s",
                this.getType(),
                this.isDone ? 1 : 0,
                this.description,
                this.from,
                this.to);
    }
}
