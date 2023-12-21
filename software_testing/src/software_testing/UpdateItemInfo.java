package software_testing;

import java.io.*;



public class UpdateItemInfo {
    private static final String FILE_PATH = "item_database.txt";

    // Method to create the file with initial data (if not already present)
    public static void initializeFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                // Initialize the file with some sample data
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                    writer.write("1,50,10\n2,30,20\n3,20,15");
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add new items or update the price of an existing item
    public static void updateItem(int itemid, double newPrice, int newQuantity) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
             BufferedWriter writer = new BufferedWriter(new FileWriter("temp_database.txt"))) {

            String line;
            boolean itemFound = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int currentItemId = Integer.parseInt(parts[0]);

                if (currentItemId == itemid) {
                    // Update the price for the specified item, quantity remains unchanged
                    writer.write(itemid + "," + newPrice + "," + parts[2] + "\n");
                    itemFound = true;
                    System.out.println("Item information updated successfully.");
                } else {
                    // Copy other items without changes
                    writer.write(line + "\n");
                }
            }

            if (!itemFound) {
                // If the item was not found, add it to the end of the file
                writer.write(itemid + "," + newPrice + "," + newQuantity + "\n");
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

    // Method to read item information from the file
    public static String readItemInfo() {
        StringBuilder itemInfo = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                itemInfo.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemInfo.toString();
    }
}

