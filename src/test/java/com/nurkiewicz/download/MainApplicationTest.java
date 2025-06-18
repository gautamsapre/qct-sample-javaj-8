package com.nurkiewicz.download;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertTrue;

public class MainApplicationTest {

    @Test
    public void testMainMethodPrintsHelloWorld() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            // Call the main method with empty args
            // Note: We're not actually running the Spring application to avoid starting the server
            // Just testing that the print statement works
            String[] args = {};
            
            // This will throw an exception when trying to start Spring, but we only care about the print
            try {
                MainApplication.main(args);
            } catch (Exception e) {
                // Expected exception when running the main method in a test environment
                // We're only testing the print statement, not the full application startup
            }
            
            // Verify that "Hello world" was printed
            String output = outContent.toString();
            assertTrue("The main method should print 'Hello world'", output.contains("Hello world"));
        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }
    }
}