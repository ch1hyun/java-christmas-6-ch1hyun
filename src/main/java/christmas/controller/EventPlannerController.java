package christmas.controller;

import christmas.view.OutputView;

public class EventPlannerController implements EntryController {
    private final OrderController orderController;
    private final EventController eventController;
    private final CalculateController calculateController;

    private EventPlannerController(OrderController orderController, EventController eventController, CalculateController calculateController) {
        this.orderController = orderController;
        this.eventController = eventController;
        this.calculateController = calculateController;
    }

    public static EventPlannerController of(OrderController orderController, EventController eventController, CalculateController calculateController) {
        return new EventPlannerController(orderController, eventController, calculateController);
    }

    @Override
    public void proceed() {
        OutputView.printStartSystem();

        requestOrder();
        requestEvent();
        requesetCalculate();
        requestShowResult();
    }

    private void requestOrder() {
        orderController.proceedOrder();
    }

    private void requestEvent() {
        eventController.proceedEvent(orderController);
    }

    private void requesetCalculate() {
        calculateController.proceedCalculate(
                orderController.getOrderAmount(),
                eventController.getRewardAmount()
        );
    }

    private void requestShowResult() {
        orderController.showResultOrderList();
        orderController.showResultSubTotal();
        eventController.showResultPresentation();
        eventController.showResultRewardList(orderController.getOrderDate());
        eventController.showResultRewardAmount();
        calculateController.showResultTotal();
        eventController.showResultBadge();
    }
}
