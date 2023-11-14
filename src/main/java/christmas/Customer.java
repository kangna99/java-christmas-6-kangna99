package christmas;

import christmas.order.OrderDetails;

public class Customer {
    private int visitDate;
    private OrderDetails orderDetails;

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