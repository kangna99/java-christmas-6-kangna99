package christmas;

public class Customer {
    private int visitDate;
    private OrderDetails orderDetails;

    public Customer() {

    }

    public void makeOrder(int date, OrderDetails orderDetails) {
        this.visitDate = date;
        this.orderDetails = orderDetails;

    }

    public int getVisitDate() {
        return visitDate;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }
}