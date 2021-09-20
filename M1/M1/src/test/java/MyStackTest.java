import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;


class MyStackTest {
    private final MyStack stack = new MyStack();

    @Test
    add

    void addTest(String input) {
        stack.push(input);
        assertEquals(input, stack.top());

        String thirdInput = input + " 3";
        stack.push(input + " 2");
        stack.push(thirdInput);
        assertEquals(thirdInput, stack.top());
        stack.delete(3);
    }

    @Test
    delete

    void deleteTest() {
        stack.push("1");
        stack.push("2");
        stack.push("3");
        //stack: 3, 2, 1
        stack.delete(2);
        //stack: 1
        assertEquals("1", stack.top());
        stack.delete(1);
    }

    @Test
    pop

    void popTest() {
        stack.push("1");
        stack.push("2");
        stack.push("3");
        assertEquals("3", stack.pop());
        assertEquals("2", stack.pop());
        assertEquals("1", stack.pop());
    }
}
