package org.transactionmanager;

import org.transactionmanager.model.Database;
import org.transactionmanager.model.Item;

import java.io.*;
import java.util.List;

public class TransactionManager {
    private final String dbPath;
    private final Database db;

    public TransactionManager(String dbPath) {
        this.dbPath = dbPath;
        this.db = new Database();
    }

    public void initializeDb() throws IOException {
        File dbFile = new File(dbPath);
        boolean created = dbFile.createNewFile();

        if (created) {
            System.out.println("Created database in " + dbFile.getAbsolutePath());
        }

        BufferedReader reader = new BufferedReader(new FileReader(dbPath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            String currentItemId = parts[0];
            int price = Integer.parseInt(parts[1]);
            int currentQuantity = Integer.parseInt(parts[2]);

            this.db.addItem(new Item(currentItemId, price, currentQuantity));
        }

        reader.close();
    }

    public void saveDb() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(dbPath));

        List<Item> allItems = this.db.getAllItems();

        for (Item item : allItems) {
            writer.write(item.toString() + "\n");
        }

        writer.close();
    }


    // Method to check if the transaction is possible and update the quantity if successful
    public void processPurchase(String itemId, int purchasedQuantity) throws IOException, IllegalArgumentException {
        Item item = this.db.getItem(itemId);

        if (item == null) {
            throw new IllegalStateException(itemId + " does not exist");
        }

        int result = item.getQuantity() - purchasedQuantity;

        if (result < 0) {
            throw new IllegalArgumentException("Cannot buy the desired quantity because there will no be enough left");
        }

        item.setQuantity(result);

        this.db.updateItem(item);
        this.saveDb();
    }

    public void processIngest(String itemId, int ingestPrice, int ingestQuantity) throws IOException {
        Item item = this.db.getItem(itemId);

        if (item == null) {
            item = new Item(itemId, ingestPrice, ingestQuantity);
        }

        this.db.addItem(item);
        this.saveDb();
    }

    public void processAddition(String itemId, int additionQuantity) throws IOException, IllegalArgumentException {
        Item item = this.db.getItem(itemId);

        if (item == null) {
            throw new IllegalStateException(itemId + " does not exist");
        }

        if (additionQuantity < 0) {
            throw new IllegalArgumentException("Addition cannot be negative");
        }

        item.setQuantity(item.getQuantity() + additionQuantity);

        this.db.updateItem(item);
        this.saveDb();
    }
    
    

    public List<Item> getAllItems() {
        return this.db.getAllItems();
    }
}

