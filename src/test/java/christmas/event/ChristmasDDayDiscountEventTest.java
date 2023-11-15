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

class ChristmasDDayDiscountEventTest {
    private ChristmasDDayDiscountEvent event;
    private OrderDetails orderDetails;

    @BeforeEach
    void setUp() {
        event = new ChristmasDDayDiscountEvent();
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("티본스테이크", "1"));
        orders.add(new Order("바비큐립", "1"));
        orders.add(new Order("초코케이크", "2"));
        orders.add(new Order("제로콜라", "1"));
        orderDetails = new OrderDetails(orders);
    }

    @Test
    @DisplayName("이벤트 적용 가능 금액 확인")
    void isApplicableAmount() {
        assertTrue(event.isApplicable(19_000, 10));
    }

    @Test
    @DisplayName("이벤트 적용 불가능 금액 확인")
    void isNotApplicableAmount() {
        assertFalse(event.isApplicable(9_999, 10));
    }

    @Test
    @DisplayName("이벤트 적용 가능 일자 확인")
    void isApplicableDay() {
        assertTrue(event.isApplicable(20_000, 25));
    }

    @Test
    @DisplayName("이벤트 적용 불가능 일자 확인")
    void isNotApplicableDay() {
        assertFalse(event.isApplicable(10_000, 30));
    }

    @Test
    @DisplayName("할인 금액 계산")
    void calculateDiscount() {
        Customer customer = new Customer(25, orderDetails);
        assertEquals(3_400, event.calculateDiscount(customer));
    }

    @Test
    @DisplayName("이벤트 이름 확인")
    void eventName() {
        assertEquals("크리스마스 디데이 할인", event.name());
    }
}