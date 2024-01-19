package org.transactionmanager.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private final Map<String, Item> items = new HashMap<>();

    public void addItem(Item item) {
        this.items.put(item.itemId, item);
    }

    public void deleteItem(Item item) {
        this.items.remove(item.itemId);
    }

    public Item getItem(String itemId) {
        return this.items.get(itemId);
    }

    public List<Item> getAllItems() {
        return this.items.values().stream().toList();
    }

    public void updateItem(Item item) {
        this.items.put(item.itemId, item);
    }
}
