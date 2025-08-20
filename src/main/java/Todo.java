public class Todo extends Task {
    public Todo(String description) throws ChoiceBotException {
        super(description);
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("You must add a description for toDo tasks. Please try again.");
        } else {
            count++;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
