package java;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


import jdk.jfr.StackTrace;

public class MyStackTest {
    private MyStack ourStack;
    @Before
    public void setup() {
        ourStack = new MyStack();
    }

    /// Koby's test
    @Test
    public void myInitialPopTest(){
        ourStack.push("FirstElement");
        assertEquals("FirstElement", ourStack.pop());
    }

    @Test AlikAddTest
    void addTest(String input) {
        ourStack.push(input);
        assertEquals(input, ourStack.top());

        String thirdInput = input + " 3";
        ourStack.push(input + " 2");
        ourStack.push(thirdInput);
        assertEquals(thirdInput, ourStack.top());
        ourStack.delete(3);
    }

    @Test AlikDeleteTest
    void deleteTest() {
        ourStack.push("1");
        ourStack.push("2");
        ourStack.push("3");
        //stack: 3, 2, 1
        ourStack.delete(2);
        //stack: 1
        assertEquals("1", ourStack.top());
        ourStack.delete(1);
    }

    @Test AliksPopTest
    void popTest() {
        ourStack.push("1");
        ourStack.push("2");
        ourStack.push("3");
        assertEquals("3", ourStack.pop());
        assertEquals("2", ourStack.pop());
        assertEquals("1", ourStack.pop());
    }

    @Test
    public void levelPeekTest() {
        String temp = "item #last";

        for (int i = 0; i < 5; i++) {
            ourStack.push("item #" + i);
        }
        ourStack.push(temp);

        assertSame(temp, ourStack.pop()); // 4, 3, 2, 1, 0

        String cur = null;
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
    void nickDeleteTest() {
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
