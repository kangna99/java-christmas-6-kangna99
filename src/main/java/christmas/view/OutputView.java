package christmas.view;

import static christmas.constants.GuideMessage.EVENT_MESSAGE;
import static christmas.constants.GuideMessage.START_MESSAGE;
import static christmas.constants.ResultMessage.BENEFIT_DETAILS;
import static christmas.constants.ResultMessage.DECEMBER_EVENT_BADGE;
import static christmas.constants.ResultMessage.GIVEAWAY_MENU;
import static christmas.constants.ResultMessage.ORDERED_MENU;
import static christmas.constants.ResultMessage.TOTAL_BENEFIT_PRICE;
import static christmas.constants.ResultMessage.TOTAL_PRICE_AFTER_DISCOUNT;
import static christmas.constants.ResultMessage.TOTAL_PRICE_BEFORE_DISCOUNT;

import christmas.event.Badge;
import christmas.order.Order;
import christmas.order.OrderDetails;

public class OutputView {
    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printPreviewDetails(int visitDate) {
        System.out.printf(EVENT_MESSAGE, visitDate);
        System.out.println();
        System.out.println();
    }

    public void printOrder(OrderDetails orderDetails) {
        System.out.println(ORDERED_MENU);
        for (Order order : orderDetails.getOrderDetails()) {
            System.out.println(order.getName() + " " + order.getCount() + "개");
        }
        System.out.println();
    }

    public void printTotalPriceBeforeDiscount(OrderDetails orderDetails) {
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT);
        System.out.printf("%,d원\n", orderDetails.calculateTotalPrice());
        System.out.println();
    }

    public void printGiveAwayMenu(String giveaway) {
        System.out.println(GIVEAWAY_MENU);
        System.out.println(giveaway);
    }

    public void printBenefitDetails(String discountAmounts, String giveaway) {
        System.out.println(BENEFIT_DETAILS);
        if (discountAmounts.isEmpty() && giveaway.isEmpty()) {
            System.out.println("없음\n");
            return;
        }
        System.out.println(discountAmounts);
        System.out.println(giveaway);
    }

    public void printTotalBenefitPrice(int price) {
        System.out.println(TOTAL_BENEFIT_PRICE);
        if (price != 0) {
            System.out.printf("-%,d원\n", price);
            System.out.println();
            return;
        }
        System.out.printf("%,d원\n", price);
        System.out.println();
    }

    public void printTotalPriceAfterDiscount(int price) {
        System.out.println(TOTAL_PRICE_AFTER_DISCOUNT);
        System.out.printf("%,d원\n", price);
        System.out.println();
    }

    public void printDecemberEventBadge(Badge badge) {
        System.out.println(DECEMBER_EVENT_BADGE);
        System.out.println(badge.getName());
    }
}