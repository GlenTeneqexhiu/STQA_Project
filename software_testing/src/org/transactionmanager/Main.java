package org.transactionmanager;


import java.io.IOException;

import org.transactionmanager.TransactionManager;

public class Main {
    static final String dbPath = "test_db.db";

    public static void main(String[] args) {
        TransactionManager tM = new TransactionManager(dbPath);
        try {
            tM.initializeDb();
            tM.processIngest("prod1", 10, 20);
        } catch (IOException e) {
            System.out.println("Error during database operation " + e);
        }
    }
}



