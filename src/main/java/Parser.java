import java.util.ArrayList;

public class Parser {
    public static Command parse(String command) throws ChoiceBotException {
        String[] commandParts = command.split(" ", 2);
        String commandWord = commandParts[0];
        String commandDescription = commandParts.length > 1 ? commandParts[1] : "";

        switch (commandWord) {
        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new ListCommand(commandDescription);
        case ("mark"):
            return new MarkCommand(commandDescription);
        case ("unmark"):
            return new UnmarkCommand(commandDescription);
        case ("delete"):
            return new DeleteCommand(commandDescription);
        case ("todo"):
            return new TodoCommand(commandDescription);
        case ("event"):
            return new EventCommand(commandDescription);
        case ("deadline"):
            return new DeadlineCommand(commandDescription);
        default:
            throw new ChoiceBotException("Invalid input. Please try again.");
        }
    }
}
