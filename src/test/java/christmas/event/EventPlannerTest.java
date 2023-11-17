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

class EventPlannerTest {
    private EventPlanner eventPlanner;

    @BeforeEach
    void setUp() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("아이스크림", "2"));
        orders.add(new Order("제로콜라", "1"));
        orders.add(new Order("티본스테이크", "2"));
        orders.add(new Order("크리스마스파스타", "1"));
        OrderDetails orderDetails = new OrderDetails(orders);
        eventPlanner = new EventPlanner(new Customer(15, orderDetails));
    }

    @Test
    @DisplayName("총 혜택 금액 확인")
    void getTotalBenefitPrice() {
        int totalBenefitPrice = eventPlanner.getTotalBenefitPrice();
        assertEquals(33_469, totalBenefitPrice);
    }

    @Test
    @DisplayName("할인 후 총 주문 금액 확인")
    void getTotalPriceAfterDiscount() {
        int totalPriceAfterDiscount = eventPlanner.getTotalPriceAfterDiscount();
        assertEquals(139_531, totalPriceAfterDiscount);
    }

    @Test
    @DisplayName("할인 내역 확인")
    void getDiscountDetails() {
        String discountDetails = eventPlanner.getDiscountDetails();
        assertNotNull(discountDetails);
    }

    @Test
    @DisplayName("증정 내역 확인")
    void getGiveawayDetails() {
        String giveawayDetails = eventPlanner.getGiveawayDetails();
        assertNotNull(giveawayDetails);
    }

    @Test
    @DisplayName("뱃지 수여 확인")
    void awardBadge() {
        Badge badge = eventPlanner.awardBadge();
        assertEquals(Badge.SANTA, badge);
    }
}