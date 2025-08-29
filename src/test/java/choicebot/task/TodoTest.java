package choicebot.task;

import choicebot.ChoiceBot;
import choicebot.ChoiceBotException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void testStringConversion(){
        // instantiating todo with second parameter true marks an [X]
        try {
            assertEquals("[T][X] read book", new Todo("read book", true).toString());
        } catch (ChoiceBotException e) {
            fail(); // the test should not reach this line
        }
        // instantiating todo with second parameter false marks []
        try {
            assertEquals("[T][ ] read book", new Todo("read book", false).toString());
        } catch (ChoiceBotException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void todo_emptyDescription_throwsException() {
        // instantiating todo with empty description throws ChoiceBotException
        assertThrows(ChoiceBotException.class, () -> new Todo("", false));
    }

    @Test
    public void todo_nullDescription_throwsException() {
        // instantiating todo with null description throws ChoiceBotException
        assertThrows(ChoiceBotException.class, () -> new Todo(null, false));
    }
}
