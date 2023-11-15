package christmas.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import christmas.order.Order;
import christmas.order.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerTest {
    private Customer customer;

    @BeforeEach
    void setUp() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("타파스", "1"));
        orders.add(new Order("제로콜라", "1"));
        OrderDetails orderDetails = new OrderDetails(orders);
        customer = new Customer(15, orderDetails);
    }

    @Test
    @DisplayName("방문 날짜 확인")
    void getVisitDate() {
        assertEquals(15, customer.getVisitDate());
    }

    @Test
    @DisplayName("주문 상세 정보 확인")
    void getOrderDetails() {
        assertNotNull(customer.getOrderDetails());
        assertEquals(2, customer.getOrderDetails().size());
    }

    @Test
    @DisplayName("총 주문 금액 확인")
    void getTotalPrice() {
        assertEquals(8_500, customer.getTotalPrice());
    }
}