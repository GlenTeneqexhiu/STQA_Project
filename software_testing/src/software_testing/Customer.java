package software_testing;

public class Customer {
    private int orderid;
    private int quantity;

    public Customer(int orderid, int quantity) {
        this.orderid = orderid;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderid;
    }

    public void setOrderId(int orderid) {
        this.orderid = orderid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

