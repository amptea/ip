public class Task {
    protected String description;
    protected boolean isDone;
    protected static int count;
    protected String type;

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
        if (count == 1) {
            System.out.println("Now you have " + count + " task in the list.");

        } else {
            System.out.println("Now you have " + count + " tasks in the list.");
        }
    }

    public void deleteTask() {
        System.out.println("Noted. I have removed the following task:");
        System.out.println("\t" + this);
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
            String dueDate = taskParts[3].trim();
            return new Deadline(description, isDone, dueDate);
        case ("E"):
            String startDate = taskParts[3].trim();
            String endDate = taskParts[4].trim();
            return new Event(description, isDone, startDate, endDate);
        default:
            throw new ChoiceBotException("Task could not be loaded properly.");
        }
    }
}
