package christmas.controller;

import christmas.constants.enums.MenuBoard;
import christmas.model.Badge;
import christmas.model.OrderAmount;
import christmas.model.OrderDate;
import christmas.model.OrderList;
import christmas.model.RewardAmount;
import christmas.model.event.DDayEvent;
import christmas.model.event.Event;
import christmas.model.event.PresentationEvent;
import christmas.model.event.SpecialEvent;
import christmas.model.event.WeekdayEvent;
import christmas.model.event.WeekendEvent;
import christmas.view.OutputView;

public class EventController {
    private Event event;
    private DDayEvent dDayEvent;
    private WeekdayEvent weekdayEvent;
    private WeekendEvent weekendEvent;
    private SpecialEvent specialEvent;
    private PresentationEvent presentationEvent;

    private RewardAmount rewardAmount;
    private Badge badge;

    public EventController() {}

    public void proceedEvent(OrderController orderController) {
        requestDiscountEvent(orderController.getOrderDate());
        requestPresentationEvent(orderController.getOrderAmount());
        generateEvent(orderController.getOrderAmount());
        requestRewardAmount(orderController.getOrderList(), orderController.getOrderDate());
        requestBadge();
    }

    private void requestDiscountEvent(OrderDate orderDate) {
        requestWeekdayEvent(orderDate);
        requestWeekendEvent(orderDate);
        requestSpecialEvent(orderDate);
        requestDDayEvent(orderDate);
    }

    private void requestWeekdayEvent(OrderDate orderDate) {
        weekdayEvent = WeekdayEvent.from(orderDate);
    }

    private void requestWeekendEvent(OrderDate orderDate) {
        weekendEvent = WeekendEvent.from(orderDate);
    }

    private void requestSpecialEvent(OrderDate orderDate) {
        specialEvent = SpecialEvent.from(orderDate);
    }

    private void requestDDayEvent(OrderDate orderDate) {
        dDayEvent = DDayEvent.from(orderDate);
    }

    private void requestPresentationEvent(OrderAmount orderAmount) {
        presentationEvent = PresentationEvent.from(orderAmount);
    }

    private void generateEvent(OrderAmount orderAmount) {
        event = Event.of(
                orderAmount,
                dDayEvent,
                weekdayEvent,
                weekendEvent,
                specialEvent,
                presentationEvent
        );
    }

    private void requestRewardAmount(OrderList orderList, OrderDate orderDate) {
        rewardAmount = RewardAmount.of(event.createDiscountList(orderList, orderDate), presentationEvent.getPresentationItem());
    }

    private void requestBadge() {
        badge = Badge.from(rewardAmount);
    }

    public void showResultPresentation() {
        OutputView.printPresentationItem(presentationEvent.getMenu());
    }

    public void showResultRewardList(OrderList orderList, OrderDate orderDate) {
        OutputView.printRewardList(
                event.toString(
                        orderList.getCountMatchMenuGroup(MenuBoard.디저트),
                        orderList.getCountMatchMenuGroup(MenuBoard.메인),
                        orderDate.getDate()
                )
        );
    }

    public void showResultRewardAmount() {
        OutputView.printRewardAmount(rewardAmount.toString());
    }

    public void showResultBadge() {
        OutputView.printBadge(badge.toString());
    }

    public RewardAmount getRewardAmount() {
        return rewardAmount;
    }
}
