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
}