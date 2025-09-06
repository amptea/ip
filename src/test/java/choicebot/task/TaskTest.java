package choicebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import choicebot.ChoiceBotException;

public class TaskTest {
    @Test
    public void getCount_instantiateTask_increasesCount() {
        TaskList tasks = new TaskList();
        try {
            Task t1 = new Task("task1", false);
            Task t2 = new Task("task2", false);
            tasks.addTask(t1);
            tasks.addTask(t2);
            // adding 2 tasks results in count of 2
            assertEquals(2, tasks.getCount());
        } catch (ChoiceBotException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void deleteMessage_deleteTask_decreasesCount() {
        TaskList tasks = new TaskList();
        try {
            Task t1 = new Task("task1", false);
            Task t2 = new Task("task2", false);
            tasks.addTask(t1);
            tasks.addTask(t2);
            // adding 2 tasks results in count of 2
            assertEquals(2, tasks.getCount());
            tasks.deleteTask(t1);
            // deleting 1 task results in count of 1
            assertEquals(1, tasks.getCount());
        } catch (ChoiceBotException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void loadTask_loadTodoTask_printsToString() {
        try {
            Task task = Task.loadTask("T | 0 | mark work");
            // loads formatted string of todo task
            assertEquals("[T][ ] mark work", task.toString());
        } catch (ChoiceBotException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void loadTask_loadEventTask_printsToString() {
        try {
            Task task = Task.loadTask("E | 0 | orbital | 2pm | 4pm");
            // load formatted string of event task
            assertEquals("[E][ ] orbital (from: 2pm to: 4pm)", task.toString());
        } catch (ChoiceBotException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void loadTask_loadDeadlineTask_printsToString() {
        try {
            Task task = Task.loadTask("D | 0 | work | 2019-10-15");
            // load formatted string of deadline task
            assertEquals("[D][ ] work (by: Oct 15 2019)", task.toString());
        } catch (ChoiceBotException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void loadTask_invalidString_throwsChoiceBotException() {
        // load formatted string of deadline task
        assertThrows(ChoiceBotException.class, () -> Task.loadTask("X | 0 | test"));
    }
}
