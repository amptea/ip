import java.util.ArrayList;

public class EventCommand extends Command {
    protected String description;

    public EventCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(ArrayList<Task> taskList) throws ChoiceBotException {
        if (!description.contains("/from ") || !description.contains("/to")) {
            throw new ChoiceBotException("Please follow format: event {description} /from {start} /to {end}.");
        }

        String[] descriptionTimeSplit = description.split("/from", 2);
        String eventName = descriptionTimeSplit[0].trim();
        String[] timeSplit = descriptionTimeSplit[1].split("/to", 2);
        String from = timeSplit[0].trim();
        String to = timeSplit[1].trim();

        if (eventName.isBlank() || from.isBlank() || to.isBlank()) {
            throw new ChoiceBotException("Please follow format: event {description} /from {start} /to {end}.");
        }

        Task event = new Event(eventName, from, to);
        taskList.add(event);
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" + event);
        event.displayCount();
    }
}
