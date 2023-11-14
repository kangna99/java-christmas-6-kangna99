package christmas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetails {
    private final List<Order> orderDetails;
    Map<Category, Integer> menuCountForEachCategory;

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

    //    private void calculateCount() {
////        initCategoryCount();
////        orderDetails.stream()
////                .map(order -> Menu.fromKoreanName(order.getName()))
////                .forEach(this::accumulateCategoryCount);
//        initCategoryCount();
//        orderDetails.stream()
//                .map(order -> Menu.fromKoreanName(order.getName()))
//                .forEach(order -> accumulateCategoryCount(order, order.getCount()));
//    }
//
    private void initCategoryCount() {
        menuCountForEachCategory = new HashMap<>();
        for (Category category : Category.values()) {
            menuCountForEachCategory.put(category, 0);
        }
    }
//
//    private void accumulateCategoryCount(Menu menu, int count) {
//        Category category = menu.getCategory();
//        int currentCount = menuCountForEachCategory.getOrDefault(category, 0);
//        menuCountForEachCategory.put(category, currentCount + count);
//    }

    private void calculateCount() {
        initCategoryCount();
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