package com.nurkiewicz.download;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void shouldAddTwoPositiveNumbers() {
        // given
        double a = 5.0;
        double b = 3.0;
        
        // when
        double result = calculator.add(a, b);
        
        // then
        assertEquals(8.0, result, 0.0001);
    }

    @Test
    public void shouldAddPositiveAndNegativeNumbers() {
        // given
        double a = 5.0;
        double b = -3.0;
        
        // when
        double result = calculator.add(a, b);
        
        // then
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void shouldSubtractTwoPositiveNumbers() {
        // given
        double a = 5.0;
        double b = 3.0;
        
        // when
        double result = calculator.subtract(a, b);
        
        // then
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void shouldSubtractWithNegativeResult() {
        // given
        double a = 3.0;
        double b = 5.0;
        
        // when
        double result = calculator.subtract(a, b);
        
        // then
        assertEquals(-2.0, result, 0.0001);
    }

    @Test
    public void shouldMultiplyTwoPositiveNumbers() {
        // given
        double a = 5.0;
        double b = 3.0;
        
        // when
        double result = calculator.multiply(a, b);
        
        // then
        assertEquals(15.0, result, 0.0001);
    }

    @Test
    public void shouldMultiplyPositiveAndNegativeNumbers() {
        // given
        double a = 5.0;
        double b = -3.0;
        
        // when
        double result = calculator.multiply(a, b);
        
        // then
        assertEquals(-15.0, result, 0.0001);
    }

    @Test
    public void shouldDivideTwoPositiveNumbers() {
        // given
        double a = 6.0;
        double b = 3.0;
        
        // when
        double result = calculator.divide(a, b);
        
        // then
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void shouldHandleDecimalDivision() {
        // given
        double a = 5.0;
        double b = 2.0;
        
        // when
        double result = calculator.divide(a, b);
        
        // then
        assertEquals(2.5, result, 0.0001);
    }

    @Test(expected = ArithmeticException.class)
    public void shouldThrowExceptionWhenDividingByZero() {
        // given
        double a = 5.0;
        double b = 0.0;
        
        // when
        calculator.divide(a, b);
        
        // then: exception is expected
    }
}