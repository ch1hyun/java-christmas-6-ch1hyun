package christmas.controller;

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
        requestOrder();
        requestEvent();
        requesetCalculate();
        requestResult();
    }

    private void requestOrder() {
        orderController.proceedOrder();
    }

    private void requestEvent() {
        eventController.proceedEvent(
                orderController.getOrderDate(),
                orderController.getOrderAmount()
        );
    }

    private void requesetCalculate() {
        calculateController.proceedCalculate(
                eventController.getRewardAmount()
        );
    }

    private void requestResult() {
        orderController.requestResultOrderList();
        orderController.requestResultSubTotal();
        eventController.requestResultPresentation();
        eventController.requestResultRewardList();
        eventController.requestResultRewardAmount();
        calculateController.requestResultTotal();
        eventController.requestResultBadge();
    }
}
