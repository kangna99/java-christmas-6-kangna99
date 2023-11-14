package christmas;

import static christmas.constants.GuideMessage.START_MESSAGE;

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
        eventPlanner = new EventPlanner();
    }

    public void run() {
        System.out.println(START_MESSAGE);
        int visitDate = inputView.readDate();
        OrderDetails orderDetails = inputView.readOrder();
        customer.makeOrder(visitDate, orderDetails);
        outputView.printDetails();
        outputView.printOrder(customer.getOrderDetails());
        outputView.printTotalPriceBeforeDiscount(customer.getOrderDetails());
        outputView.printGiveAwayMenu();
        outputView.printBenefitDetails(eventPlanner.getDiscountAmounts(customer));
        outputView.printTotalBenefitPrice(eventPlanner.getTotalBenefitPrice(customer));
        outputView.printTotalPriceAfterDiscount();
        outputView.printDecemberEventBadge();
    }
}
