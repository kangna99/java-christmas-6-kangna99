package christmas.constants;

public class ErrorMessage {
    public static final String CONTAINS_WHITESPACE = "공백이 포함되어 있습니다.";
    public static final String DATE_NOT_NUMBER = "유효하지 않은 날짜입니다.";
    public static final String INVALID_DATE = "유효하지 않은 날짜입니다.";
    public static final String INVALID_ORDER_MENU_NAME = "유효하지 않은 주문입니다.";
    public static final String INVALID_ORDER_MENU_COUNT = "유효하지 않은 주문입니다.";
    public static final String INVALID_ORDER_FORMAT = "유효하지 않은 주문입니다.";
    public static final String ORDER_COUNT_NOT_NUMBER = "유효하지 않은 주문입니다.";
    public static final String DUPLICATED_ORDER_MENU_NAME = "유효하지 않은 주문입니다.";
    public static final String NOT_ALLOWED_ONLY_BEVERAGE = "음료만 주문 시, 주문할 수 없습니다.";
    public static final String NOT_ALLOWED_OVER_MENU_COUNT = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.";
    public static final String REQUEST_INPUT_RETRY = "다시 입력해 주세요.";

    public static String formatErrorWithRetry(String error) {
        return "[ERROR] " + error + " " + REQUEST_INPUT_RETRY;
    }
}
