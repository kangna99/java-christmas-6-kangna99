package christmas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetails {
    private final List<Order> orderDetails;
    Map<Category, Integer> categoryCount;

    public OrderDetails(List<Order> orderDetails) {
        validate(orderDetails);
        this.orderDetails = orderDetails;
        calculateCount();
    }

    private void validate(List<Order> orderDetails) {
        if (orderDetails == null) {
            throw new IllegalArgumentException("주문 내역이 null입니다.");
        }
    }

    private void calculateCount() {
        initCategoryCount();
        orderDetails.stream()
                .map(order -> Menu.valueOf(order.getName()))
                .forEach(this::accumulateCategoryCount);
    }

    private void initCategoryCount() {
        categoryCount = new HashMap<>();
        for (Category category : Category.values()) {
            categoryCount.put(category, 0);
        }
    }

    private void accumulateCategoryCount(Menu menu) {
        Category category = menu.getCategory();
        int currentCount = categoryCount.getOrDefault(category, 0);
        categoryCount.put(category, currentCount + 1);
    }

    public List<Order> getOrderDetails() {
        return orderDetails;
    }

    public int getDessertCount() {
        return categoryCount.get(Category.DESSERT);
    }

    public int getMainDishCount() {
        return categoryCount.get(Category.MAIN_DISH);
    }
}