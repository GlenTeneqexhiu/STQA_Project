package software_testing;

import java.io.*;

public class TransactionManager {
    private static final String FILE_PATH = "item_database.txt";

    // Method to check if the transaction is possible and update the quantity if successful
    public static void processTransaction(int itemid, int requiredQuantity) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
             BufferedWriter writer = new BufferedWriter(new FileWriter("temp_database.txt"))) {

            String line;
            boolean itemFound = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int currentItemId = Integer.parseInt(parts[0]);
                int currentQuantity = Integer.parseInt(parts[2]);

                if (currentItemId == itemid) {
                    if (currentQuantity >= requiredQuantity) {
                        // Update the quantity for the specified item by subtracting the required quantity
                        int updatedQuantity = currentQuantity - requiredQuantity;
                        writer.write(currentItemId + "," + parts[1] + "," + updatedQuantity + "\n");
                        itemFound = true;
                        System.out.println("Transaction successful.");
                    } else {
                        // Insufficient quantity for the transaction
                        System.out.println("Transaction failed. Insufficient quantity.");
                    }
                } else {
                    // Copy other items without changes
                    writer.write(line + "\n");
                }
            }

            if (!itemFound) {
                // Item not found in the database
                System.out.println("Item not found in the database.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Rename the temporary file to the original file
        File tempFile = new File("temp_database.txt");
        File originalFile = new File(FILE_PATH);

        if (tempFile.renameTo(originalFile)) {
            System.out.println("Database updated successfully.");
        } else {
            System.out.println("Failed to update database.");
        }
    }
}
