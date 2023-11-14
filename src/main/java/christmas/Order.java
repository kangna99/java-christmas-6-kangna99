package christmas;

import static christmas.constants.ErrorMessage.CONTAINS_WHITESPACE;
import static christmas.constants.ErrorMessage.INVALID_ORDER_MENU_NAME;
import static christmas.constants.ErrorMessage.NOT_NUMBER;
import static christmas.constants.ErrorMessage.NOT_POSITIVE_NUMBER;
import static christmas.constants.ErrorMessage.formatErrorWithRetry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order {
    private static final String WHITESPACE_REGEX = "\\s";

    public Order(String menuName, String count) {
        validateName(menuName);
        validateCount(count);
    }

    private void validateName(String name) {
        validateNotBlank(name);
        validateNameInMenu(name);
    }

    private void validateCount(String count) {
        validateNotBlank(count);
        validateInputIsNumeric(count);
        validateInputIsPositiveNumber(count);
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isBlank() || containsWhitespace(input)) {
            throw new IllegalArgumentException(formatErrorWithRetry(CONTAINS_WHITESPACE));
        }
    }

    private boolean containsWhitespace(String input) {
        Pattern pattern = Pattern.compile(WHITESPACE_REGEX);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private void validateInputIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(formatErrorWithRetry(NOT_NUMBER));
        }
    }

    private void validateInputIsPositiveNumber(String input) {
        int number = Integer.parseInt(input);
        if (number < 1) {
            throw new IllegalArgumentException(formatErrorWithRetry(NOT_POSITIVE_NUMBER));
        }
    }

    private void validateNameInMenu(String name) {
        if (!Menu.contains(name)) {
            throw new IllegalArgumentException(formatErrorWithRetry(INVALID_ORDER_MENU_NAME));
        }
    }

//    public void add(String menuName, int count) {
//        orderDetails.put(Menu.valueOf(menuName), count);
//    }
}