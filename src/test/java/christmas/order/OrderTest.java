package christmas.order;

import static christmas.constants.ErrorMessage.CONTAINS_WHITESPACE;
import static christmas.constants.ErrorMessage.INVALID_ORDER_MENU_COUNT;
import static christmas.constants.ErrorMessage.INVALID_ORDER_MENU_NAME;
import static christmas.constants.ErrorMessage.ORDER_COUNT_NOT_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @Test
    @DisplayName("유효한 주문 생성")
    void validOrderCreation() {
        Order order = new Order("아이스크림", "3");
        assertEquals("아이스크림", order.getName());
        assertEquals(3, order.getCount());
    }

    @Test
    @DisplayName("유효하지 않은 주문 생성 - 빈 이름")
    void invalidOrderCreationBlankName() {
        assertThatThrownBy(() -> new Order("", "3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CONTAINS_WHITESPACE);
    }

    @Test
    @DisplayName("유효하지 않은 주문 생성 - 이름에 공백 포함")
    void invalidOrderCreationWhitespaceName() {
        assertThatThrownBy(() -> new Order("초코 케이크", "2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CONTAINS_WHITESPACE);
    }

    @Test
    @DisplayName("유효하지 않은 주문 생성 - 존재하지 않는 메뉴 이름")
    void invalidOrderCreationUnknownMenu() {
        assertThatThrownBy(() -> new Order("파스타", "2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER_MENU_NAME);
    }

    @Test
    @DisplayName("유효하지 않은 주문 생성 - 빈 수량")
    void invalidOrderCreationBlankCount() {
        assertThatThrownBy(() -> new Order("아이스크림", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CONTAINS_WHITESPACE);
    }

    @Test
    @DisplayName("유효하지 않은 주문 생성 - 수량이 숫자가 아님")
    void invalidOrderCreationNonNumericCount() {
        assertThatThrownBy(() -> new Order("아이스크림", "abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ORDER_COUNT_NOT_NUMBER);
    }

    @Test
    @DisplayName("유효하지 않은 주문 생성 - 1개 미만")
    void invalidOrderCreationCountOutOfRangeMin() {
        assertThatThrownBy(() -> new Order("아이스크림", "0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER_MENU_COUNT);
    }

    @Test
    @DisplayName("유효하지 않은 주문 생성 - 20개 초과")
    void invalidOrderCreationCountOutOfRangeMax() {
        assertThatThrownBy(() -> new Order("아이스크림", "21"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER_MENU_COUNT);
    }
}