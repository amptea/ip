import java.util.ArrayList;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void execute(ArrayList<Task> taskList) {
        ChoiceBot.say("ChoiceBot", ": Thanks for stopping by! See you again!");
    }
}
