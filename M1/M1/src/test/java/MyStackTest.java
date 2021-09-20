package java;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyStackTest {
    private MyStack ourStack;
    @Before
    public void setup() {
        ourStack = new MyStack();
    }

    @Test
    public void kobyInitialPopTest(){
        ourStack.push("FirstElement");
        assertEquals("FirstElement", ourStack.pop());
    }

    @Test
    public void alikAddTest() {
        String s = "test";
        ourStack.push(s);
        assertEquals(s, ourStack.top());

        String thirdInput = s + " 3";
        ourStack.push(s + " 2");
        ourStack.push(thirdInput);
        assertEquals(thirdInput, ourStack.top());
        ourStack.delete(3);
    }

    @Test
    public void alikDeleteTest() {
        ourStack.push("1");
        ourStack.push("2");
        ourStack.push("3");
        //stack: 3, 2, 1
        ourStack.delete(2);
        //stack: 1
        assertEquals("1", ourStack.top());
        ourStack.delete(1);
    }

    @Test
    public void alikPopTest() {
        ourStack.push("1");
        ourStack.push("2");
        ourStack.push("3");
        assertEquals("3", ourStack.pop());
        assertEquals("2", ourStack.pop());
        assertEquals("1", ourStack.pop());
    }

    @Test
    public void andrewLevelPeekTest() {
        String temp = "item #last";

        for (int i = 0; i < 5; i++) {
            ourStack.push("item #" + i);
        }
        ourStack.push(temp);

        assertSame(temp, ourStack.pop()); // 4, 3, 2, 1, 0

        String cur;
        for (int i = 0; i < 5; i++) {
            cur = ourStack.top();
            assertNotNull(cur);
            assertEquals(cur, ourStack.pop());
        }

        assertNull(ourStack.top());
    }

    @Test
    public void joePushTest() {
        ourStack.push("1");
        assertEquals("1", ourStack.top());
        ourStack.push("2");
        assertEquals("1", ourStack.top());
        ourStack.push("3");
        assertEquals("1", ourStack.top());
        ourStack.delete(3);
    }
  
    @Test
    public void nickDeleteTest() {
        ourStack.push("a");
        assertEquals("a", ourStack.top());
        ourStack.push("b");
        assertEquals("b", ourStack.top());
        ourStack.push("c");
        assertEquals("c", ourStack.top());
        ourStack.delete(3);
        assertNull(ourStack.top());
    }
}
