package christmas.controller;

import christmas.model.Badge;
import christmas.model.DiscountItem;
import christmas.model.DiscountList;
import christmas.model.OrderAmount;
import christmas.model.OrderDate;
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

    public void proceedEvent(OrderDate orderDate, OrderAmount orderAmount) {
        requestDiscountEvent(orderDate);
        requestPresentationEvent(orderAmount);
        generateEvent();
        requestRewardAmount();
        requestBadge();
    }

    private void requestDiscountEvent(OrderDate orderDate) {
        List<DiscountItem> discountList = new ArrayList<>();

        requestWeekdayEvent(orderDate, discountList);
        requestWeekendEvent(orderDate, discountList);
        requestSpecialEvent(orderDate, discountList);
        requestDDayEvent(orderDate, discountList);

        generateDiscountList(discountList);
    }

    private void generateDiscountList(List<DiscountItem> discountList) {
        this.discountList = DiscountList.from(discountList);
    }

    private void requestWeekdayEvent(OrderDate orderDate, List<DiscountItem> discountList) {
        weekdayEvent = WeekdayEvent.from(orderDate);

        if (weekdayEvent.isAcitve()) {
            discountList.add(DiscountItem.of(weekdayEvent.getDiscountType(), orderDate.getDate()));
        }
    }

    private void requestWeekendEvent(OrderDate orderDate, List<DiscountItem> discountList) {
        weekendEvent = WeekendEvent.from(orderDate);

        if (weekendEvent.isAcitve()) {
            discountList.add(DiscountItem.of(weekendEvent.getDiscountType(), orderDate.getDate()));
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

    private void generateEvent() {
        event = Event.of(
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

    public void showResultRewardList(OrderDate orderDate) {
        OutputView.printRewardList(event.toString(orderDate.getDate()));
    }

    public void showResultRewardAmount() {
        OutputView.printRewardAmount(rewardAmount.toString());
    }
}
