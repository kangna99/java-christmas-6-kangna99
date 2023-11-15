package christmas.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.order.Order;
import christmas.order.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekendDiscountEventTest {
    private WeekendDiscountEvent event;
    private OrderDetails orderDetails;

    @BeforeEach
    void setUp() {
        event = new WeekendDiscountEvent();
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("해산물파스타", "1"));
        orders.add(new Order("크리스마스파스타", "1"));
        orders.add(new Order("시저샐러드", "1"));
        orders.add(new Order("티본스테이크", "1"));
        orders.add(new Order("초코케이크", "2"));
        orderDetails = new OrderDetails(orders);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("이벤트 적용 가능 일자 확인")
    void isApplicableOnFridayAndSaturday(int visitDate) {
        assertTrue(event.isApplicableDay(visitDate));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    @DisplayName("이벤트 적용 불가능 일자 확인")
    void isNotApplicableOnFridayAndSaturday(int visitDate) {
        assertFalse(event.isApplicableDay(visitDate));
    }

    @Test
    @DisplayName("할인 금액 계산")
    void calculateDiscount() {
        Customer customer = new Customer(1, orderDetails);
        assertEquals(6_069, event.calculateDiscount(customer));
    }

    @Test
    @DisplayName("이벤트 이름 확인")
    void eventName() {
        assertEquals("주말 할인", event.name());
    }
}