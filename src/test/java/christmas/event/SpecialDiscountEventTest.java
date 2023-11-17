package christmas.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.order.Order;
import christmas.order.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountEventTest {
    private SpecialDiscountEvent event;
    private OrderDetails orderDetails;

    @BeforeEach
    void setUp() {
        event = new SpecialDiscountEvent();
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("티본스테이크", "1"));
        orders.add(new Order("바비큐립", "1"));
        orders.add(new Order("초코케이크", "2"));
        orders.add(new Order("제로콜라", "1"));
        orderDetails = new OrderDetails(orders);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
            27, 28, 29, 30, 31})
    @DisplayName("이벤트 적용 가능 일자 확인")
    void isApplicable(int visitDate) {
        boolean isExpected = visitDate == 3 || visitDate == 10 || visitDate == 17 || visitDate == 24 || visitDate == 25
                || visitDate == 31;
        assertEquals(isExpected, event.isApplicable(10_000, visitDate));
    }

    @Test
    @DisplayName("할인 금액 계산")
    void calculateDiscount() {
        Customer customer = new Customer(25, orderDetails);
        assertEquals(1_000, event.calculateDiscount(customer));
    }

    @Test
    @DisplayName("이벤트 이름 확인")
    void eventName() {
        assertEquals("특별 할인", event.name());
    }
}