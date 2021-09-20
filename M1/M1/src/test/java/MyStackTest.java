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

class MyStackTest {
    private MyStack ourStack;
    @Before
    public void setup() {
        ourStack = new MyStack();
    }

    @Test
    void myInitialPopTest(){
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

    @Test JoePushTest
    void joePushTest() {
        ourStack.push("1");
        assertEquals("1", ourStack.top());
        ourStack.push("2");
        assertEquals("1", ourStack.top());
        ourStack.push("3");
        assertEquals("1", ourStack.top());
        ourStack.delete(3);
    }
}
