package org.example;

public class CalculatorTest {
    @Test
    public void testSumSuccess() {
        Calculator calculator = new Calculator(1, 2);
        int result = calculator.sum();
        System.out.println(result);
        Assertions.assertEquals(3, result, "Result not equals");
        Assertions.assertNotEquals(2, result, null);
    }

    @Test
    public void testSumFailed() {
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
