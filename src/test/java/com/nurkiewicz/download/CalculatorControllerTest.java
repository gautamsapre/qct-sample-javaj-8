package com.nurkiewicz.download;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorControllerTest {

    @Mock
    private Calculator calculator;

    @InjectMocks
    private CalculatorController calculatorController;

    @Before
    public void setUp() {
        // Setup mock behavior
        when(calculator.add(5.0, 3.0)).thenReturn(8.0);
        when(calculator.subtract(5.0, 3.0)).thenReturn(2.0);
        when(calculator.multiply(5.0, 3.0)).thenReturn(15.0);
        when(calculator.divide(6.0, 3.0)).thenReturn(2.0);
        when(calculator.divide(5.0, 0.0)).thenThrow(new ArithmeticException("Division by zero is not allowed"));
    }

    @Test
    public void shouldReturnCorrectAdditionResult() {
        // when
        ResponseEntity<Map<String, Object>> response = calculatorController.add(5.0, 3.0);
        
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(8.0, response.getBody().get("result"));
        assertEquals("+", response.getBody().get("operation"));
        assertEquals(5.0, response.getBody().get("a"));
        assertEquals(3.0, response.getBody().get("b"));
    }

    @Test
    public void shouldReturnCorrectSubtractionResult() {
        // when
        ResponseEntity<Map<String, Object>> response = calculatorController.subtract(5.0, 3.0);
        
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2.0, response.getBody().get("result"));
        assertEquals("-", response.getBody().get("operation"));
    }

    @Test
    public void shouldReturnCorrectMultiplicationResult() {
        // when
        ResponseEntity<Map<String, Object>> response = calculatorController.multiply(5.0, 3.0);
        
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(15.0, response.getBody().get("result"));
        assertEquals("*", response.getBody().get("operation"));
    }

    @Test
    public void shouldReturnCorrectDivisionResult() {
        // when
        ResponseEntity<Map<String, Object>> response = calculatorController.divide(6.0, 3.0);
        
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2.0, response.getBody().get("result"));
        assertEquals("/", response.getBody().get("operation"));
    }

    @Test
    public void shouldReturnErrorForDivisionByZero() {
        // when
        ResponseEntity<Map<String, Object>> response = calculatorController.divide(5.0, 0.0);
        
        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().containsKey("error"));
        assertEquals("/", response.getBody().get("operation"));
    }
}