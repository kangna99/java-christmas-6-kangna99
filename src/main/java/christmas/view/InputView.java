package christmas.view;

import static christmas.constants.Constant.WHITESPACE_REGEX;
import static christmas.constants.ErrorMessage.CONTAINS_WHITESPACE;
import static christmas.constants.ErrorMessage.DATE_NOT_NUMBER;
import static christmas.constants.ErrorMessage.INVALID_DATE;
import static christmas.constants.ErrorMessage.INVALID_ORDER_FORMAT;
import static christmas.constants.ErrorMessage.formatErrorWithRetry;
import static christmas.constants.GuideMessage.REQUEST_DATE;
import static christmas.constants.GuideMessage.REQUEST_ORDER;

import camp.nextstep.edu.missionutils.Console;
import christmas.order.Order;
import christmas.order.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final int MINIMUM_DECEMBER_DATE = 1;
    private static final int MAXIMUM_DECEMBER_DATE = 31;
    private static final String COMMA_DELIMITER = ",";
    private static final String HYPHEN_DELIMITER = "-";

    public int readDate() {
        while (true) {
            System.out.println(REQUEST_DATE);
            String input = Console.readLine();
            try {
                validateDate(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public OrderDetails readOrder() {
        while (true) {
            System.out.println(REQUEST_ORDER);
            String input = Console.readLine();
            try {
                validateOrder(input);
                List<Order> orderDetails = createOrderDetails(input);
                return new OrderDetails(orderDetails);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Order> createOrderDetails(String input) {
        List<String> orders = List.of(input.split(COMMA_DELIMITER));
        List<Order> orderDetails = new ArrayList<>();
        orders.forEach(order -> {
            List<String> menuDetails = List.of(order.split(HYPHEN_DELIMITER));
            orderDetails.add(new Order(menuDetails.get(0), menuDetails.get(1)));
        });
        return orderDetails;
    }

    private void validateDate(String date) {
        validateNotBlank(date);
        validateInputIsNumeric(date);
        validateInputIsWithinRange(date);
    }

    private void validateOrder(String order) {
        validateNotBlank(order);
        validateFormat(order);
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
            throw new IllegalArgumentException(formatErrorWithRetry(DATE_NOT_NUMBER));
        }
    }

    private void validateInputIsWithinRange(String input) {
        int date = Integer.parseInt(input);
        if (date < MINIMUM_DECEMBER_DATE || date > MAXIMUM_DECEMBER_DATE) {
            throw new IllegalArgumentException(formatErrorWithRetry(INVALID_DATE));
        }
    }

    private void validateFormat(String order) {
        String regex = "([가-힣]+)-(\\d+)(,|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(order);
        if (!matcher.find()) {
            throw new IllegalArgumentException(formatErrorWithRetry(INVALID_ORDER_FORMAT));
        }
    }
}