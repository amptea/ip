public class Task {
    protected String description;
    protected boolean isDone;
    protected static int count;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
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
}
