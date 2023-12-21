package software_testing;

public class Item {
    private int itemid;
    private double price;
    private int quantity;

    public Item(int itemid, double price, int quantity) {
        this.itemid = itemid;
        this.quantity = quantity;
        setPrice(price); // Using the setter to check and set the price
    }

    public void setPrice(double price) {
        // Check if the price is within the specified boundaries
        if (price > 0 && price <= 100.0) {
            this.price = price;
            System.out.println("Price set.");
        } else {
            System.out.println("Invalid price.");
        }
    }

    public double getPrice() {
        return price;
    }


}
