package org.example;

import org.example.exception.AssertionException;

public class Assertions {

    public static void assertEquals(Object expected, Object actual, String message) throws AssertionException {
        if (!expected.equals(actual)) {
            if (message == null) {
                throw new AssertionException("Expected " + expected + ", but was " + actual);
            } else {
                throw new AssertionException(message);
            }
        }
    }

    public static void assertNotEquals(Object expected, Object actual, String message) throws AssertionException {
        if (expected.equals(actual)) {
            if (message == null) {
                throw new AssertionException("Expected " + expected + ", but was " + actual);
            } else {
                throw new AssertionException(message);
            }
        }
    }
}
