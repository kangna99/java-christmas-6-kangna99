package christmas.event;

import christmas.order.OrderDetails;

public class Customer {
    private int visitDate;
    private OrderDetails orderDetails;

    public Customer(int visitDate, OrderDetails orderDetails) {
        this.visitDate = visitDate;
        this.orderDetails = orderDetails;
    }

    public int getVisitDate() {
        return visitDate;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }
}