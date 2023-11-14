package christmas.view;

import static christmas.constants.GuideMessage.EVENT_MESSAGE;
import static christmas.constants.ResultMessage.BENEFIT_DETAILS;
import static christmas.constants.ResultMessage.DECEMBER_EVENT_BADGE;
import static christmas.constants.ResultMessage.GIVEAWAY_MENU;
import static christmas.constants.ResultMessage.ORDERED_MENU;
import static christmas.constants.ResultMessage.TOTAL_BENEFIT_PRICE;
import static christmas.constants.ResultMessage.TOTAL_PRICE_AFTER_DISCOUNT;
import static christmas.constants.ResultMessage.TOTAL_PRICE_BEFORE_DISCOUNT;

import christmas.Order;
import christmas.OrderDetails;
import christmas.events.DiscountEvent;
import java.util.Map;

public class OutputView {
    public void printOrder(OrderDetails orderDetails) {
        System.out.println(ORDERED_MENU);
        for (Order order : orderDetails.getOrderDetails()) {
            System.out.println(order.getName() + " " + order.getCount() + "개");
        }
        System.out.println();
    }

    public void printDetails() {
        System.out.println(EVENT_MESSAGE);
        System.out.println();
    }

    public void printTotalPriceBeforeDiscount(OrderDetails orderDetails) {
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT);
        System.out.printf("%,d원\n", orderDetails.calculateTotalPrice());
        System.out.println();
    }

    public void printGiveAwayMenu() {
        System.out.println(GIVEAWAY_MENU);
        System.out.println();

    }

    public void printBenefitDetails(Map<DiscountEvent, Integer> discountAmounts) {
        System.out.println(BENEFIT_DETAILS);
        System.out.println();

    }

    public void printTotalBenefitPrice(int price) {
        System.out.println(TOTAL_BENEFIT_PRICE);
        System.out.printf("-%,d원\n", price);
        System.out.println();
    }

    public void printTotalPriceAfterDiscount(int price) {
        System.out.println(TOTAL_PRICE_AFTER_DISCOUNT);
        System.out.printf("%,d원\n", price);
        System.out.println();
    }

    public void printDecemberEventBadge() {
        System.out.println(DECEMBER_EVENT_BADGE);
        System.out.println();
    }
}