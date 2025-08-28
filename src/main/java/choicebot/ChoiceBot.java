package choicebot;

import choicebot.command.Command;
import choicebot.command.ExitCommand;
import choicebot.parser.Parser;
import choicebot.storage.Storage;
import choicebot.task.TaskList;
import choicebot.ui.UI;

import java.util.Scanner;

public class ChoiceBot {
    public static void main(String[] args) {
        new ChoiceBot("data/tasks.txt");
    }

    public ChoiceBot(String filePath) {
        Scanner sc = new Scanner(System.in);
        boolean isEnded = false;
        Storage storage = new Storage(filePath);
        TaskList tasks;
        UI ui = new UI();
        ui.welcome();

        try {
            tasks = new TaskList(storage.loadFile().getTaskList());
        } catch (ChoiceBotException e) {
            ui.displayError(e);
            tasks = new TaskList();
        }

        while (!isEnded) {
            String input = sc.nextLine();

            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui);
                if (command instanceof ExitCommand) {
                    isEnded = true;
                }
            } catch (ChoiceBotException e) {
                ui.displayMessage(e.getMessage());
            }
        }
    }
}
