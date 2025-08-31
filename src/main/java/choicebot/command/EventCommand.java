package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.Event;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Represents a command that creates and adds a Event task to tasklist.
 * An Event follows the format: event {description} /from {time} /to {time}
 */
public class EventCommand extends Command {
    protected String description;

    /**
     * Constructs an EventCommand with the given description.
     *
     * @param description Contains name of Event, and start and end date/time.
     */
    public EventCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Event command by creating new Event instance.
     * The Event instance is added to given task list and saved to storage.
     * Displays a confirmation message through given UI.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @throws ChoiceBotException If command does not have /from, /to, or if name, start, or end date/time missing.
     */
    @Override
    public void execute(TaskList tasks, UI ui) throws ChoiceBotException {
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

        Task event = new Event(eventName, false, from, to);
        tasks.addTask(event);
        Storage.saveFile(tasks);
        ui.addTaskMessage(event);
    }
}
