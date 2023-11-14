package christmas.view;

import static christmas.constants.ResultMessage.ORDERED_MENU;
import static christmas.constants.ResultMessage.TOTAL_PRICE_BEFORE_DISCOUNT;

import christmas.Menu;
import christmas.Order;
import christmas.OrderDetails;

public class OutputView {
    public void printOrder(OrderDetails orderDetails) {
        System.out.println(ORDERED_MENU);
        for (Order order : orderDetails.getOrderDetails()) {
            System.out.println(order.getName() + " " + order.getCount() + "개");
        }
    }

    public void printTotalPrice(OrderDetails orderDetails) {
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT);
        long sum = 0;
        for (Order order : orderDetails.getOrderDetails()) {
            sum += Menu.findMenu(order.getName()).getPrice() * order.getCount();
        }
        System.out.printf("%,d원", sum);
    }
}