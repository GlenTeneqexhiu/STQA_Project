package test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.transactionmanager.TransactionManager;
import org.transactionmanager.model.Item;

import test.utils.DbFileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.Assert.*;

public class TestTransactionManager {
    public static String testDBPath = "transaction_manager_test.db";
    public static String copyTestDBPath = "transaction_manager_test_copy.db";
    private static TransactionManager transactionManager;

    @BeforeClass
    public static void setUp() throws IOException {
        Path source = Path.of(new File(testDBPath).toURI());
        Path target = Path.of(new File(copyTestDBPath).toURI());
        
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        transactionManager = new TransactionManager(target.toString());
        transactionManager.initializeDb();
    }

    @Test
    public void testIngestion() {
        try {
            String itemId = "prod3";
            int ingestQuantity = 20;
            int ingestPrice = 20;

            transactionManager.processIngest(itemId, ingestPrice, ingestQuantity);

            Item item = transactionManager.getAllItems().stream().filter(i -> i.itemId.equals(itemId)).findFirst().orElse(null);

            assertNotNull(item);
            assertEquals(item.getQuantity(), ingestQuantity);
            assertEquals(item.getPrice(), ingestPrice);

            assertTrue(DbFileUtils.containsItem(copyTestDBPath, item));

        } catch (IOException e) {
            fail("Could not run the test");
        }
    }
    @Test
    public void additiontest() {
        try {
            String itemId = "prod2";
            int addQuantity = 20;
           

            transactionManager.processAddition(itemId,addQuantity);

            Item item = transactionManager.getAllItems().stream().filter(i -> i.itemId.equals(itemId)).findFirst().orElse(null);

            assertNotNull(item);
            assertEquals(item.getQuantity(), addQuantity);

            assertTrue(DbFileUtils.containsItem(copyTestDBPath, item));

        } catch (IOException e) {
            fail("Could not run the test");
        }
    }

    @Test
    public void testFailedPurchase() {
        String prod2Id = "prod2";

        assertThrows(
                IllegalArgumentException.class,
                () -> transactionManager.processPurchase(prod2Id, 20)
        );

        Item item = transactionManager.getAllItems().stream().filter(i -> i.itemId.equals(prod2Id)).findFirst().orElse(null);

        assertNotNull(item);
        assertEquals(item.getQuantity(), 0);

    }
    
    @Test
    public void testFailedAdditiontest() {
        String prod2Id = "prod2";

        assertThrows(
                IllegalArgumentException.class,
                () -> transactionManager.processAddition(prod2Id, -20)
        );

        Item item = transactionManager.getAllItems().stream().filter(i -> i.itemId.equals(prod2Id)).findFirst().orElse(null);

        assertNotNull(item);
        assertEquals(item.getQuantity(), 20);

    }

    @Test
    public void processPurchasesuccesstest() {
        try {
            String itemId = "prod3";
            int ingestQuantity = 18;
            

            transactionManager.processPurchase(itemId,ingestQuantity);

            Item item = transactionManager.getAllItems().stream().filter(i -> i.itemId.equals(itemId)).findFirst().orElse(null);

            assertNotNull(item);
            assertEquals(item.getQuantity(), 2);
            

            assertTrue(DbFileUtils.containsItem(copyTestDBPath, item));

        } catch (IOException e) {
            fail("Could not run the test");
        }
    }

}
