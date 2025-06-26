package com.nurkiewicz.download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller that exposes calculator operations as HTTP endpoints.
 */
@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final Calculator calculator;

    @Autowired
    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Adds two numbers.
     *
     * @param a first number
     * @param b second number
     * @return JSON response with the result
     */
    @GetMapping("/add")
    public ResponseEntity<Map<String, Object>> add(
            @RequestParam double a,
            @RequestParam double b) {
        double result = calculator.add(a, b);
        return createSuccessResponse(a, b, "+", result);
    }

    /**
     * Subtracts second number from the first.
     *
     * @param a first number
     * @param b second number
     * @return JSON response with the result
     */
    @GetMapping("/subtract")
    public ResponseEntity<Map<String, Object>> subtract(
            @RequestParam double a,
            @RequestParam double b) {
        double result = calculator.subtract(a, b);
        return createSuccessResponse(a, b, "-", result);
    }

    /**
     * Multiplies two numbers.
     *
     * @param a first number
     * @param b second number
     * @return JSON response with the result
     */
    @GetMapping("/multiply")
    public ResponseEntity<Map<String, Object>> multiply(
            @RequestParam double a,
            @RequestParam double b) {
        double result = calculator.multiply(a, b);
        return createSuccessResponse(a, b, "*", result);
    }

    /**
     * Divides first number by the second.
     *
     * @param a first number (dividend)
     * @param b second number (divisor)
     * @return JSON response with the result
     */
    @GetMapping("/divide")
    public ResponseEntity<Map<String, Object>> divide(
            @RequestParam double a,
            @RequestParam double b) {
        try {
            double result = calculator.divide(a, b);
            return createSuccessResponse(a, b, "/", result);
        } catch (ArithmeticException e) {
            return createErrorResponse(a, b, "/", e.getMessage());
        }
    }

    private ResponseEntity<Map<String, Object>> createSuccessResponse(double a, double b, String operation, double result) {
        Map<String, Object> response = new HashMap<>();
        response.put("operation", operation);
        response.put("a", a);
        response.put("b", b);
        response.put("result", result);
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<Map<String, Object>> createErrorResponse(double a, double b, String operation, String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("operation", operation);
        response.put("a", a);
        response.put("b", b);
        response.put("error", errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Error handler for invalid parameters.
     *
     * @param ex the exception
     * @return JSON response with error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleErrors(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Invalid input: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}