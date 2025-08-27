import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;

    public Deadline(String description, Boolean isDone, LocalDate dueDate) throws ChoiceBotException {
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
        return "[D]" + super.toString() + " (by: " + convertDate(dueDate) + ")";
    }

    @Override
    public String saveTask() {
        return String.format("%s | %d | %s | %s",
                this.getType(),
                this.isDone ? 1 : 0,
                this.description,
                this.dueDate);
    }

    public String convertDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
