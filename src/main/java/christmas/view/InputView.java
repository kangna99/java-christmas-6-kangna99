package christmas.view;

import static christmas.constants.ErrorMessage.CONTAINS_WHITESPACE;
import static christmas.constants.ErrorMessage.INVALID_DATE;
import static christmas.constants.ErrorMessage.NOT_NUMBER;
import static christmas.constants.ErrorMessage.formatErrorWithRetry;
import static christmas.constants.GuideMessage.REQUEST_DATE;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final String WHITESPACE_REGEX = "\\s";

    public int readDate() {
        while (true) {
            System.out.println(REQUEST_DATE);
            String date = Console.readLine();
            try {
                validateDate(date);
                return Integer.parseInt(date);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateDate(String date) {
        validateNotBlank(date);
        validateInputIsNumeric(date);
        validateInputIsWithinRange(date);
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

    private void validateInputIsWithinRange(String input) {
        int date = Integer.parseInt(input);
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(formatErrorWithRetry(INVALID_DATE));
        }
    }
}