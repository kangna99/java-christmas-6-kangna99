package christmas.order;

import static christmas.constants.ErrorMessage.DUPLICATED_ORDER_MENU_NAME;
import static christmas.constants.ErrorMessage.NOT_ALLOWED_ONLY_BEVERAGE;
import static christmas.constants.ErrorMessage.NOT_ALLOWED_OVER_MENU_COUNT;
import static christmas.constants.ErrorMessage.formatErrorWithRetry;

import christmas.menu.Category;
import christmas.menu.Menu;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderDetails implements Iterable<Order> {
    private static final int DEFAULT_COUNT = 0;
    private static final int MAXIMUM_ORDER_COUNT = 20;
    private final List<Order> orderDetails;
    private Map<Category, Integer> menuCountForEachCategory;

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
        Set<String> menuNames = new HashSet<>();

        if (orderDetails.stream().anyMatch(order -> !menuNames.add(order.getName()))) {
            throw new IllegalArgumentException(formatErrorWithRetry(DUPLICATED_ORDER_MENU_NAME));
        }
    }

    private void validateOrderQuantity() {
        if (calculateTotalCount() > MAXIMUM_ORDER_COUNT) {
            throw new IllegalArgumentException(formatErrorWithRetry(NOT_ALLOWED_OVER_MENU_COUNT));
        }
    }

    private void validateNotOnlyBeverage() {
        boolean hasOnlyBeverage = orderDetails.stream()
                .map(order -> Menu.called(order.getName()))
                .allMatch(menu -> menu.getCategory() == Category.BEVERAGE);

        if (hasOnlyBeverage) {
            throw new IllegalArgumentException(formatErrorWithRetry(NOT_ALLOWED_ONLY_BEVERAGE));
        }
    }

    private void initCategoryCount() {
        menuCountForEachCategory = new HashMap<>();
        for (Category category : Category.values()) {
            menuCountForEachCategory.put(category, DEFAULT_COUNT);
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
        Menu menu = Menu.called(order.getName());
        Category category = menu.getCategory();
        int currentCount = menuCountForEachCategory.getOrDefault(category, DEFAULT_COUNT);
        menuCountForEachCategory.put(category, currentCount + order.getCount());
    }

    public int calculateTotalPrice() {
        return orderDetails.stream()
                .mapToInt(Order::getPrice)
                .sum();
    }

    public int getDessertCount() {
        return menuCountForEachCategory.get(Category.DESSERT);
    }

    public int getMainDishCount() {
        return menuCountForEachCategory.get(Category.MAIN_DISH);
    }

    @Override
    public Iterator<Order> iterator() {
        return orderDetails.iterator();
    }

    public int size() {
        return orderDetails.size();
    }
}