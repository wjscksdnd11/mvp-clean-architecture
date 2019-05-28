package com.example.mvpclean;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void stack_pop_test(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        System.out.println(stack.size());
        stack.pop();
        System.out.println(stack.size());
        assertTrue(true);
    }
}