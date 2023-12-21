package software_testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ItemTest {

    @Test
    public void testSetPrice_ValidPrice_PrintsPriceSet() {
        // Test equivalence class: Valid price (between 1 and 100)
        Item item = new Item(1, 10.0, 5);  // Replace with the actual constructor and class
        String expectedOutput = "Price set";
        
        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        item.setPrice(50);  // Replace with the actual method

        // Reset System.out to the standard output stream
        System.setOut(System.out);

        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testSetPrice_InvalidPrice_PrintsInvalidPrice() {
        // Test equivalence class: Invalid price (less than 1)
        Item item = new Item(1, 10.0, 5);  // Replace with the actual constructor and class
        String expectedOutput = "Invalid price";
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        item.setPrice(-5);  // Replace with the actual method

        System.setOut(System.out);

        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testSetPrice_AnotherInvalidPrice_PrintsInvalidPrice() {
        // Test equivalence class: Invalid price (greater than 100)
        Item item = new Item(1, 10.0, 5);  // Replace with the actual constructor and class
        String expectedOutput = "Invalid price";
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        item.setPrice(120);  // Replace with the actual method

        System.setOut(System.out);

        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    // Additional tests for boundary values and other scenarios can be added
}


