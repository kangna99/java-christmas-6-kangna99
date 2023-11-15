package christmas.view;

import static christmas.constants.ErrorMessage.CONTAINS_WHITESPACE;
import static christmas.constants.ErrorMessage.DATE_NOT_NUMBER;
import static christmas.constants.ErrorMessage.INVALID_DATE;
import static christmas.constants.ErrorMessage.INVALID_ORDER_FORMAT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {
    private final InputView inputView = new InputView();

    @Test
    @DisplayName("유효한 날짜 검증")
    void validateValidDate() {
        assertDoesNotThrow(() -> inputView.validateDate("15"));
    }

    @Test
    @DisplayName("숫자가 아닌 문자를 포함한 날짜 입력")
    void validateInvalidDateWithNonNumericCharacter() {
        assertThatThrownBy(() -> inputView.validateDate("1a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DATE_NOT_NUMBER);
    }

    @Test
    @DisplayName("범위를 벗어난 날짜 입력")
    void validateInvalidDateOutOfRange() {
        assertThatThrownBy(() -> inputView.validateDate("32"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    @DisplayName("유효한 주문 형식 검증")
    void validateValidOrder() {
        assertDoesNotThrow(() -> inputView.validateOrder("아이스크림-2,티본스테이크-1"));
    }

    @Test
    @DisplayName("공백이 포함된 주문 입력")
    void validateInvalidOrderWithWhitespace() {
        assertThatThrownBy(() -> inputView.validateOrder("아이스크림 -2, 티본스테이크-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CONTAINS_WHITESPACE);
    }

    @Test
    @DisplayName("잘못된 형식의 주문 입력")
    void validateInvalidOrderWithInvalidFormat() {
        assertThatThrownBy(() -> inputView.validateOrder("아이스크림-2,티본스테이크-1,"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER_FORMAT);
    }
}