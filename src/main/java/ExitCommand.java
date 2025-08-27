import java.util.ArrayList;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, UI ui) {
        ui.exitMessage();
    }
}
