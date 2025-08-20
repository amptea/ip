public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String description, String dueDate) throws ChoiceBotException {
        super(description);
        this.dueDate = dueDate;
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
}
