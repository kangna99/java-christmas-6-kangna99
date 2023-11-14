package christmas;

import java.util.List;

public class OrderDetails {
    private final List<Order> orderDetails;

    public OrderDetails(List<Order> orderDetails) {
        this.orderDetails = orderDetails;
        validate(orderDetails);
    }

    private void validate(List<Order> orderDetails) {
        if (orderDetails == null) {
            throw new IllegalArgumentException("주문 내역이 null입니다.");
        }
    }

    public void add(Order order) {
        orderDetails.add(order);
    }

    public List<Order> getOrderDetails() {
        return orderDetails;
    }
}