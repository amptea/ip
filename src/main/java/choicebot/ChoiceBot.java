package choicebot;

import java.util.Scanner;

import choicebot.command.Command;
import choicebot.command.ExitCommand;
import choicebot.parser.Parser;
import choicebot.storage.Storage;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * The main class of ChoiceBot.
 * ChoiceBot is a chatbot used to add, delete and manage tasks using command inputs.
 */
public class ChoiceBot {
    /**
     * Constructs and runs an instance of ChoiceBot using the specified file path.
     *
     * @param filePath File used to load and save task list data.
     */
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

    /**
     * The entry point of the chatbot.
     *
     * @param args Command-line arguments (Not used yet).
     */
    public static void main(String[] args) {
        new ChoiceBot("data/tasks.txt");
    }
}
