public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String description, Boolean isDone, String dueDate) throws ChoiceBotException {
        super(description, isDone);
        this.dueDate = dueDate;
        this.type = "D";
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("You must add a description for Deadline tasks. Please try again.");
        }
        if (dueDate == null) {
            throw new ChoiceBotException("You must add a due date for Deadline tasks. Please try again.");
        } else {
            count++;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

    @Override
    public String saveTask() {
        return String.format("%s | %d | %s | %s",
                this.getType(),
                this.isDone ? 1 : 0,
                this.description,
                this.dueDate);
    }
}
