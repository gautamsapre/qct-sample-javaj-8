package com.nurkiewicz.download;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculatorIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldAddTwoNumbers() {
        // when
        ResponseEntity<Map> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/calculator/add?a=5&b=3", Map.class);
        
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(8.0, response.getBody().get("result"));
    }

    @Test
    public void shouldSubtractTwoNumbers() {
        // when
        ResponseEntity<Map> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/calculator/subtract?a=5&b=3", Map.class);
        
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2.0, response.getBody().get("result"));
    }

    @Test
    public void shouldMultiplyTwoNumbers() {
        // when
        ResponseEntity<Map> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/calculator/multiply?a=5&b=3", Map.class);
        
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(15.0, response.getBody().get("result"));
    }

    @Test
    public void shouldDivideTwoNumbers() {
        // when
        ResponseEntity<Map> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/calculator/divide?a=6&b=3", Map.class);
        
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2.0, response.getBody().get("result"));
    }

    @Test
    public void shouldReturnErrorForDivisionByZero() {
        // when
        ResponseEntity<Map> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/calculator/divide?a=5&b=0", Map.class);
        
        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().containsKey("error"));
    }
}