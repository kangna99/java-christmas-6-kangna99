package christmas.order;

import static christmas.constants.ErrorMessage.DUPLICATED_ORDER_MENU_NAME;
import static christmas.constants.ErrorMessage.NOT_ALLOWED_ONLY_BEVERAGE;
import static christmas.constants.ErrorMessage.NOT_ALLOWED_OVER_MENU_COUNT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderDetailsTest {
    @Test
    @DisplayName("유효한 OrderDetails 생성")
    void validOrderDetailsCreation() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("아이스크림", "2"));
        orders.add(new Order("티본스테이크", "1"));

        OrderDetails orderDetails = new OrderDetails(orders);

        assertEquals(2, orderDetails.size());
        assertEquals(2, orderDetails.getDessertCount());
        assertEquals(1, orderDetails.getMainDishCount());
    }

    @Test
    @DisplayName("유효하지 않은 OrderDetails 생성 - 중복된 주문")
    void invalidOrderDetailsCreationDuplicatedOrder() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("아이스크림", "2"));
        orders.add(new Order("티본스테이크", "1"));
        orders.add(new Order("아이스크림", "1")); // 중복된 주문

        assertThatThrownBy(() -> new OrderDetails(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DUPLICATED_ORDER_MENU_NAME);
    }

    @Test
    @DisplayName("유효하지 않은 OrderDetails 생성 - 음료만 있는 경우")
    void invalidOrderDetailsCreationOnlyBeverage() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("제로콜라", "2"));
        orders.add(new Order("레드와인", "1"));

        assertThatThrownBy(() -> new OrderDetails(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_ALLOWED_ONLY_BEVERAGE);
    }

    @Test
    @DisplayName("유효하지 않은 OrderDetails 생성 - 주문 수량 초과")
    void invalidOrderDetailsCreationExceedMaxCount() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("아이스크림", "15"));
        orders.add(new Order("초코케이크", "10"));
        assertThatThrownBy(() -> new OrderDetails(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_ALLOWED_OVER_MENU_COUNT);
    }
}