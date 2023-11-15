package christmas.controller;

import christmas.constants.enums.MenuBoard;
import christmas.model.Badge;
import christmas.model.DiscountItem;
import christmas.model.DiscountList;
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
import java.util.ArrayList;
import java.util.List;

public class EventController {
    private Event event;
    private DDayEvent dDayEvent;
    private WeekdayEvent weekdayEvent;
    private WeekendEvent weekendEvent;
    private SpecialEvent specialEvent;
    private PresentationEvent presentationEvent;

    private DiscountList discountList;
    private RewardAmount rewardAmount;
    private Badge badge;

    public EventController() {}

    public void proceedEvent(OrderController orderController) {
        requestDiscountEvent(orderController.getOrderDate(), orderController.getOrderList());
        requestPresentationEvent(orderController.getOrderAmount());
        generateEvent(orderController.getOrderAmount());
        requestRewardAmount();
        requestBadge();
    }

    private void requestDiscountEvent(OrderDate orderDate, OrderList orderList) {
        List<DiscountItem> discountList = new ArrayList<>();

        requestWeekdayEvent(orderDate, orderList, discountList);
        requestWeekendEvent(orderDate, orderList, discountList);
        requestSpecialEvent(orderDate, discountList);
        requestDDayEvent(orderDate, discountList);

        generateDiscountList(discountList);
    }

    private void generateDiscountList(List<DiscountItem> discountList) {
        this.discountList = DiscountList.from(discountList);
    }

    private void requestWeekdayEvent(OrderDate orderDate, OrderList orderList, List<DiscountItem> discountList) {
        weekdayEvent = WeekdayEvent.from(orderDate);

        if (weekdayEvent.isAcitve()) {
            discountList.add(DiscountItem.of(weekdayEvent.getDiscountType(), orderList.getCountMatchMenuGroup(MenuBoard.디저트)));
        }
    }

    private void requestWeekendEvent(OrderDate orderDate, OrderList orderList, List<DiscountItem> discountList) {
        weekendEvent = WeekendEvent.from(orderDate);

        if (weekendEvent.isAcitve()) {
            discountList.add(DiscountItem.of(weekendEvent.getDiscountType(), orderList.getCountMatchMenuGroup(MenuBoard.메인)));
        }
    }

    private void requestSpecialEvent(OrderDate orderDate, List<DiscountItem> discountList) {
        specialEvent = SpecialEvent.from(orderDate);

        if (specialEvent.isAcitve()) {
            discountList.add(DiscountItem.of(specialEvent.getDiscountType(), orderDate.getDate()));
        }
    }

    private void requestDDayEvent(OrderDate orderDate, List<DiscountItem> discountList) {
        dDayEvent = DDayEvent.from(orderDate);

        if (dDayEvent.isAcitve()) {
            discountList.add(DiscountItem.of(dDayEvent.getDiscountType(), orderDate.getDate()));
        }
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

    private void requestRewardAmount() {
        rewardAmount = RewardAmount.of(discountList, presentationEvent.getPresentationItem());
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
