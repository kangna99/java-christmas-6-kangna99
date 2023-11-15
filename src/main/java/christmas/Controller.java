package christmas;

import christmas.order.OrderDetails;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final Customer customer;
    private final EventPlanner eventPlanner;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        customer = new Customer();
        eventPlanner = new EventPlanner(customer);
    }

    public void run() {
        outputView.printStartMessage();
        int visitDate = inputView.readDate();
        OrderDetails orderDetails = inputView.readOrder();
        customer.makeOrder(visitDate, orderDetails);
        outputView.printPreviewDetails(visitDate);
        outputView.printOrder(customer.getOrderDetails());
        outputView.printTotalPriceBeforeDiscount(customer.getOrderDetails());
        outputView.printGiveAwayMenu(eventPlanner.getGiveaway(customer));
        outputView.printBenefitDetails(eventPlanner.getDiscountDetails(), eventPlanner.getGiveawayDetails());
        outputView.printTotalBenefitPrice(eventPlanner.getTotalBenefitPrice());
        outputView.printTotalPriceAfterDiscount(eventPlanner.getTotalPriceAfterDiscount());
        outputView.printDecemberEventBadge(eventPlanner.awardBadge());
    }
}