package christmas.controller;

import christmas.model.OrderAmount;
import christmas.model.OrderDate;
import christmas.model.OrderList;

public class EventPlannerController implements EntryController {
    private final OrderController orderController;
    private final EventController eventController;
    private final CalculateController calculateController;
    private final ResultController resultController;

    private EventPlannerController(OrderController orderController, EventController eventController, CalculateController calculateController, ResultController resultController) {
        this.orderController = orderController;
        this.eventController = eventController;
        this.calculateController = calculateController;
        this. resultController = resultController;
    }

    public static EventPlannerController of(OrderController orderController, EventController eventController, CalculateController calculateController, ResultController resultController) {
        return new EventPlannerController(orderController, eventController, calculateController, resultController);
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
