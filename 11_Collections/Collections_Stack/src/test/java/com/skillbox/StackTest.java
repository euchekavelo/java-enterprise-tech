package com.skillbox;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.*;
import java.util.stream.Collectors;

public class StackTest {
    private com.skillbox.Stack<String> stack;

    @Before
    public void setUp() throws Exception {
        Class<?> stackInterface = com.skillbox.Stack.class;
        Reflections reflections = new Reflections("com.skillbox", new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class)
                .stream()
                .collect(Collectors.toSet());
        for (Class<?> clazz : classes) {
            HashSet<Class<?>> interfaces = new HashSet<>(Arrays.asList(clazz.getInterfaces()));
            for (Class<?> i : interfaces) {
                if (i.equals(stackInterface)) {
                    Object o = clazz.newInstance();
                    stack = (Stack<String>) o;
                }
            }
        }
        if(stack != null) {
            stack.setMaxSize(10);
        }
    }

    @Test(expected = StackException.class)
    public void testPush() throws StackException {
        for (int i = 0; i < 12; i++) {
            stack.push("hello" + i);
        }
    }

    @Test(expected = StackException.class)
    public void testPop() throws StackException {
        for (int i = 0; i < 1; i++) {
            stack.push("hello" + i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.peek());
            stack.pop();
        }
    }

    @Test
    public void testPeek() throws StackException {
        for (int i = 0; i < 1; i++) {
            stack.push("hello" + i);
        }
        String peek = stack.peek();
        Assert.assertEquals("hello0", peek);
    }

    @Test
    public void testGetSize() throws StackException {
        for (int i = 0; i < 1; i++) {
            stack.push("hello" + i);
        }
        int size = stack.getSize();
        Assert.assertEquals(1, size);
    }

    @Test
    public void testIsEmpty() throws StackException {
        boolean stackEmpty = stack.isEmpty();
        Assert.assertTrue(stackEmpty);
        stack.push("hello1");
        stackEmpty = stack.isEmpty();
        Assert.assertFalse(stackEmpty);
    }

    @Test
    public void testIsFull() throws StackException {
        for (int i = 0; i < 10; i++) {
            stack.push("hello" + i);
        }
        boolean isFull = stack.isFull();
        Assert.assertTrue(isFull);
    }

    @Test
    public void testPushAll() throws StackException {
        List<String> strings = Arrays.asList("1", "2", "3");
        stack.pushAll(strings);
        int size = stack.getSize();
        Assert.assertEquals(3, size);
    }

    @Test
    public void testPopAll() throws StackException {
        List<String> strings = Arrays.asList("1", "2", "3");
        stack.pushAll(strings);
        int size = stack.getSize();
        List<String> actual = new ArrayList<>();
        stack.popAll(actual);
        Assert.assertEquals(strings.stream().sorted((a,b) -> -a.compareTo(b)).collect(Collectors.toList()), actual);
        size = stack.getSize();
        Assert.assertEquals(0, size);
    }
}
