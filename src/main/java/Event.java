public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws ChoiceBotException{
        super(description);
        this.from = from;
        this.to = to;
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("You must add a description for Event tasks. Please try again.");
        }
        if (from == null) {
            throw new ChoiceBotException("You must add a valid start date/time for Event tasks. Please try again.");
        }
        if (to == null) {
            throw new ChoiceBotException("You must add a valid end date/time for Event tasks. Please try again.");
        } else {
            count++;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + "to: " + this.to + ")";
    }
}
