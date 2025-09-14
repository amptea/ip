package choicebot.task;

import choicebot.ChoiceBotException;

/**
 * Represents an Event task.
 * <p>
 * An Event task requires a description, a start date/time, and an end date/time
 * </p>
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with the given description, completion status, start and end date/time.
     *
     * @param description Description of Event task.
     * @param isDone Whether the task is marked as completed.
     * @param from Start date/time.
     * @param to End date/time.
     * @throws ChoiceBotException If description is null or blank, or if from or to are null.
     */
    public Event(String description, Boolean isDone, String from, String to) throws ChoiceBotException {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.type = "E";
        if (description.isBlank()) {
            throw new ChoiceBotException(
                    "You must add a description for choicebot.task.Event tasks. Please try again.");
        }
        if (from == null) {
            throw new ChoiceBotException(
                    "You must add a valid start date/time for choicebot.task.Event tasks. Please try again.");
        }
        if (to == null) {
            throw new ChoiceBotException(
                    "You must add a valid end date/time for choicebot.task.Event tasks. Please try again.");
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Prepends the string with E to indiciate it is an Event task.
     * Appends start and end date/time to the String description.
     * </p>
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * {@inheritDoc}
     * <p>
     * Appends start and end date/time to the saved String format.
     * </p>
     */
    @Override
    public String saveTask() {
        assert this.type != null : "Task type must be specified";
        assert this.description != null : "Task description must be specified";
        assert this.from != null || this.to != null : "From/To date must be specified";
        return String.format("%s | %d | %s | %s | %s",
                this.getType(),
                this.isDone ? 1 : 0,
                this.description,
                this.from,
                this.to);
    }
}
