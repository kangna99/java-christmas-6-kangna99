package christmas.order;

import static christmas.constants.ErrorMessage.DUPLICATED_ORDER_MENU_NAME;
import static christmas.constants.ErrorMessage.NOT_ALLOWED_ONLY_BEVERAGE;
import static christmas.constants.ErrorMessage.NOT_ALLOWED_OVER_MENU_COUNT;
import static christmas.constants.ErrorMessage.formatErrorWithRetry;

import christmas.menu.Category;
import christmas.menu.Menu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetails {
    private final List<Order> orderDetails;
    Map<Category, Integer> menuCountForEachCategory;

    public OrderDetails(List<Order> orderDetails) {
        this.orderDetails = orderDetails;
        initCategoryCount();
        validate();
        calculateCategoryCount();
    }

    private void validate() {
        validateDuplicated();
        validateOrderQuantity();
        validateNotOnlyBeverage();
    }

    private void validateDuplicated() {
        if (orderDetails.stream().distinct().count() != orderDetails.size()) {
            throw new IllegalArgumentException(formatErrorWithRetry(DUPLICATED_ORDER_MENU_NAME));
        }
    }

    private void validateOrderQuantity() {
        if (calculateTotalCount() > 20) {
            throw new IllegalArgumentException(formatErrorWithRetry(NOT_ALLOWED_OVER_MENU_COUNT));
        }
    }

    private void validateNotOnlyBeverage() {
        boolean hasOnlyBeverage = orderDetails.stream()
                .map(order -> Menu.fromKoreanName(order.getName()))
                .allMatch(menu -> menu.getCategory() == Category.BEVERAGE);

        if (hasOnlyBeverage) {
            throw new IllegalArgumentException(formatErrorWithRetry(NOT_ALLOWED_ONLY_BEVERAGE));
        }
    }

    private void initCategoryCount() {
        menuCountForEachCategory = new HashMap<>();
        for (Category category : Category.values()) {
            menuCountForEachCategory.put(category, 0);
        }
    }

    private int calculateTotalCount() {
        return orderDetails.stream()
                .mapToInt(Order::getCount)
                .sum();
    }

    private void calculateCategoryCount() {
        orderDetails.stream()
                .forEach(this::accumulateCategoryCount);
    }

    private void accumulateCategoryCount(Order order) {
        Menu menu = Menu.fromKoreanName(order.getName());
        Category category = menu.getCategory();
        int currentCount = menuCountForEachCategory.getOrDefault(category, 0);
        menuCountForEachCategory.put(category, currentCount + order.getCount());
    }

    public int calculateTotalPrice() {
        return orderDetails.stream()
                .mapToInt(Order::getPrice)
                .sum();
    }

    public List<Order> getOrderDetails() {
        return orderDetails;
    }

    public int getDessertCount() {
        return menuCountForEachCategory.get(Category.DESSERT);
    }

    public int getMainDishCount() {
        return menuCountForEachCategory.get(Category.MAIN_DISH);
    }
}