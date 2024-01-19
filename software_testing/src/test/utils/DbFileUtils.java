package test.utils;

import org.transactionmanager.model.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DbFileUtils {
    public static boolean containsItem(String dbPath, Item toCheck) throws IOException {
        File dbFile = new File(dbPath);

        if (!dbFile.exists()) {
            return false;
        }

        BufferedReader reader = new BufferedReader(new FileReader(dbPath));
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.equals(toCheck.toString())) {
                return true;
            }
        }

        reader.close();

        return false;
    }
}
