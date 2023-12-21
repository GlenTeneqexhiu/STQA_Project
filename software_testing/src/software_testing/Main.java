package software_testing;


import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Redirect the output to a file
        try (PrintStream fileOutput = new PrintStream(new FileOutputStream("output_log.txt"))) {
            // Initialize the file (if not already present)
            UpdateItemInfo.initializeFile();

            // Example: Updating item information (replace with actual data)
            UpdateItemInfo.updateItem(2, 35, 25);

            // Example: Processing a transaction (replace with actual data)
            TransactionManager.processTransaction(2, 5);

            // Example: Displaying current item information
            fileOutput.println("Current Item Information:\n" + UpdateItemInfo.readItemInfo());

            System.out.println("Output logged to output_log.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}



