package org.example;

import org.example.annotation.After;
import org.example.annotation.Before;
import org.example.annotation.Test;
import org.example.exception.AssertionException;

public class CalculatorTest {

    @Test
    public void testSumSuccess() throws AssertionException {
        Calculator calculator = new Calculator(1, 2);
        int result = calculator.sum();
        System.out.println(result);
        Assertions.assertEquals(3, result, "Result not equals");
        Assertions.assertNotEquals(2, result, null);
    }

    @Test
    public void testSumFailed() throws AssertionException {
        Calculator calculator = new Calculator(1, 2);
        int result = calculator.sum();
        System.out.println(result);
        Assertions.assertEquals(2, result, "Result not equals");
    }

    @Before
    public void before() {
        System.out.println("message: before");
    }

    @After
    public void after() {
        System.out.println("message: after");
    }
}
