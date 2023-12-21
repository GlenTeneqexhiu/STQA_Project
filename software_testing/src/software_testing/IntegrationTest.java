package software_testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class IntegrationTest {

    @BeforeEach
    void setUp() {
        // Initialize the file with some sample data
        UpdateItemInfo.initializeFile();
    }

    @Test
    void testSuccessfulTransaction() {
        // Perform a successful transaction and check if the database is updated correctly
        int itemId = 1;
        int initialQuantity = 10;
        int requiredQuantity = 5;

        // Ensure the item has sufficient quantity for the transaction
        assertEquals("File created: item_database.txt", UpdateItemInfo.initializeFile());
        assertEquals("Transaction successful.\nDatabase updated successfully.", processTransaction(itemId, initialQuantity, requiredQuantity));
    }

    @Test
    void testFailedTransactionInsufficientQuantity() {
        // Attempt a transaction with insufficient quantity and check if it fails gracefully
        int itemId = 2;
        int initialQuantity = 20;
        int requiredQuantity = 25;

        // Ensure the item does not have sufficient quantity for the transaction
        assertEquals("File created: item_database.txt", UpdateItemInfo.initializeFile());
        assertEquals("Transaction failed. Insufficient quantity.\nFailed to update database.", processTransaction(itemId, initialQuantity, requiredQuantity));
    }

    @Test
    void testUpdateItemInfo() {
        // Update item information and check if the database is updated correctly
        int itemId = 3;
        double newPrice = 25.0;
        int newQuantity = 30;

        // Ensure the item is updated correctly
        assertEquals("File created: item_database.txt", UpdateItemInfo.initializeFile());
        assertEquals("Item information updated successfully.\nDatabase updated successfully.", updateItem(itemId, newPrice, newQuantity));
    }

    private String processTransaction(int itemId, int initialQuantity, int requiredQuantity) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TransactionManager.processTransaction(itemId, requiredQuantity);

        System.setOut(System.out);

        return outputStream.toString().trim();
    }

    private String updateItem(int itemId, double newPrice, int newQuantity) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        UpdateItemInfo.updateItem(itemId, newPrice, newQuantity);

        System.setOut(System.out);

        return outputStream.toString().trim();
    }
}
