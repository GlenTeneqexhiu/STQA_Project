package software_testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionManagerTest {
	

    @Test
    public void testProcessTransaction_ItemNotFound_PrintsItemNotFound() {
        // Test scenario: Item not found in the database
        UpdateItemInfo.initializeFile();
        TransactionManager transactionManager = new TransactionManager();
        String expectedOutput = "Item not found in the database";
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        transactionManager.processTransaction(4, 5);  // Replace with the actual method

        System.setOut(System.out);

        assertEquals(expectedOutput, outputStream.toString().trim());
    }
    
    

    @Test
    public void testProcessTransaction_InsufficientQuantity_PrintsTransactionFailed() {
        // Test scenario: Insufficient quantity
        UpdateItemInfo.initializeFile();
        TransactionManager transactionManager = new TransactionManager();
        String expectedOutput = "Transaction failed. Insufficient quantity";
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        transactionManager.processTransaction(2, 25);  // Replace with the actual method

        System.setOut(System.out);

        assertEquals(expectedOutput, outputStream.toString().trim());
    }

	
    @Test
    public void testProcessTransaction_SuccessfulTransaction_PrintsTransactionSuccessful() {
        // Test scenario: Successful transaction
        UpdateItemInfo.initializeFile();
        TransactionManager transactionManager = new TransactionManager();
        String expectedOutput = "Transaction successful";
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        transactionManager.processTransaction(2, 5);  // Replace with the actual method

        System.setOut(System.out);

        assertEquals(expectedOutput, outputStream.toString().trim());

        // Additional check for database update (assuming it's a method in UpdateItemInfo class)
        String updatedDatabase = UpdateItemInfo.readItemInfo();
        assertTrue(updatedDatabase.contains("2,30,15"));  // Replace with the actual expected updated quantity
    }

   
}

