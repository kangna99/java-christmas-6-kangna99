package christmas;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails {
    private final List<Order> orderDetails;

    public OrderDetails() {
        orderDetails = new ArrayList<>();
    }

    public void add(Order order) {
        orderDetails.add(order);
    }
}