package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.TaskList;
import choicebot.ui.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineCommandTest {
    private TaskList tasks;
    private UI ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new UI();

        File testFile = new File("data/test_tasks.txt");
        if (testFile.exists()) {
            testFile.delete();
        }
        storage = new Storage("data/test_tasks.txt");
    }

    @Test
    public void execute_validDeadline_addsTask() throws ChoiceBotException {
        String input = "Deadline /by 2025-08-29";
        DeadlineCommand deadline = new DeadlineCommand(input);
        deadline.execute(tasks, ui);

        // executing deadline command adds deadline event to tasklist
        assertEquals(1, tasks.size());
        assertEquals("[D][ ] Deadline (by: Aug 29 2025)", tasks.getTask(0).toString());
    }

    @Test
    public void execute_invalidDeadlineFormat_throwsException() {
        String input = "Submit report /by 29-08-2025"; // Wrong deadline format
        DeadlineCommand deadline = new DeadlineCommand(input);

        // executing deadline command throws ChoiceBotException
        assertThrows(ChoiceBotException.class, () -> {
            deadline.execute(tasks, ui);
        });
    }

    @Test
    public void execute_missingBy_throwsException() {
        String input = "Submit report 29-08-2025"; // Missing /by
        DeadlineCommand deadline = new DeadlineCommand(input);

        // executing deadline command throws ChoiceBotException
        assertThrows(ChoiceBotException.class, () -> {
            deadline.execute(tasks, ui);
        });
    }

    @Test
    public void execute_missingDate_throwsException() {
        String input = "Submit report /by"; // Missing /by
        DeadlineCommand deadline = new DeadlineCommand(input);

        // executing deadline command throws ChoiceBotException
        assertThrows(ChoiceBotException.class, () -> {
            deadline.execute(tasks, ui);
        });
    }
}
