package org.transactionmanager.model;

public class Item {
    public final String itemId;
    private int price;
    private int quantity;

    public Item(String itemId, int price, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price; // Using the setter to check and set the price
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        // Check if the price is within the specified boundaries
        if (price > 0 && price <= 100.0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Invalid price");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return this.itemId + "," + this.price + "," + this.quantity;
    }
}
