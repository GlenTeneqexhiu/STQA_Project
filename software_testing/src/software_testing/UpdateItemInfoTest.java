package software_testing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateItemInfoTest {

    private static final String TEST_FILE_PATH = "test_item_database.txt";

    @BeforeEach
    public void setUp() {
        // Initialize the test file with some sample data before each test
        UpdateItemInfo.initializeFile();
    }

    @Test
    public void testUpdateItem_UpdateExistingItemAndAddNewItem() {
        // Update the price of an existing item
        UpdateItemInfo.updateItem(2, 25, 10);

        // Add a new item
        UpdateItemInfo.updateItem(4, 40, 20);

        // Expected data after updates
        String expectedData = "1,50,10\n2,25,20\n3,20,15\n4,40,20";

        // Read the content of the file and compare with the expected data
        try {
            String actualData = Files.readString(Paths.get(TEST_FILE_PATH));
            assertEquals(expectedData, actualData);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception occurred while reading the file");
        }
    }

    @Test
    public void testReadItemInfo_ReturnsCorrectData() {
        // Expected initial data
        String expectedData = "1,50,10\n2,30,20\n3,20,15";

        // Read the content of the file and compare with the expected data
        String actualData = UpdateItemInfo.readItemInfo();
        assertEquals(expectedData, actualData);
    }

}


