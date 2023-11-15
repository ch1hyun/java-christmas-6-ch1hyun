package christmas.model.event;

import christmas.constants.EventConstant;
import christmas.constants.OutputMessage;
import christmas.constants.enums.MenuBoard;
import christmas.model.DiscountItem;
import christmas.model.DiscountList;
import christmas.model.OrderAmount;
import christmas.model.OrderDate;
import christmas.model.OrderList;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private final Activation activation;
    private final DDayEvent dDayEvent;
    private final WeekdayEvent weekdayEvent;
    private final WeekendEvent weekendEvent;
    private final SpecialEvent specialEvent;
    private final PresentationEvent presentationEvent;

    private Event(Activation activation, DDayEvent dDayEvent, WeekdayEvent weekdayEvent, WeekendEvent weekendEvent, SpecialEvent specialEvent,
                 PresentationEvent presentationEvent) {
        this.activation = activation;
        this.dDayEvent = dDayEvent;
        this.weekdayEvent = weekdayEvent;
        this.weekendEvent = weekendEvent;
        this.specialEvent = specialEvent;
        this.presentationEvent = presentationEvent;
    }

    public static Event of(OrderAmount orderAmount, DDayEvent dDayEvent, WeekdayEvent weekdayEvent, WeekendEvent weekendEvent, SpecialEvent specialEvent,
                           PresentationEvent presentationEvent) {
        Boolean active = EventConstant.FALSE;

        if (orderAmount.isGreaterThanOrEqualTo(EventConstant.EVENT_ACTIVE_CRITERIA)) {
            active = EventConstant.TRUE;
        }

        return new Event(
                Activation.from(active),
                dDayEvent,
                weekdayEvent,
                weekendEvent,
                specialEvent,
                presentationEvent
        );
    }

    public Boolean isActive() {
        return activation.isActive();
    }

    public DiscountList createDiscountList(OrderList orderList, OrderDate orderDate) {
        List<DiscountItem> discountList = new ArrayList<>();

        if (isActive()) {
            addDDayEventItem(discountList, orderDate);
            addWeekdayEventItem(discountList, orderList);
            addWeekendEventItem(discountList, orderList);
            addSpecialEventItem(discountList, orderDate);
        }

        return DiscountList.from(discountList);
    }

    private void addWeekdayEventItem(List<DiscountItem> discountList, OrderList orderList) {
        if (weekdayEvent.isAcitve()
                && orderList.getCountMatchMenuGroup(weekdayEvent.getMenuBoard()) > EventConstant.ZERO) {
            discountList.add(DiscountItem.of(weekdayEvent.getDiscountType(), orderList.getCountMatchMenuGroup(MenuBoard.디저트)));
        }
    }

    private void addWeekendEventItem(List<DiscountItem> discountList, OrderList orderList) {
        if (weekendEvent.isAcitve()
                && orderList.getCountMatchMenuGroup(weekendEvent.getMenuBoard()) > EventConstant.ZERO) {
            discountList.add(DiscountItem.of(weekendEvent.getDiscountType(), orderList.getCountMatchMenuGroup(MenuBoard.메인)));
        }
    }

    private void addSpecialEventItem(List<DiscountItem> discountList, OrderDate orderDate) {
        if (specialEvent.isAcitve()) {
            discountList.add(DiscountItem.of(specialEvent.getDiscountType(), orderDate.getDate()));
        }
    }

    private void addDDayEventItem(List<DiscountItem> discountList, OrderDate orderDate) {
        if (dDayEvent.isAcitve()) {
            discountList.add(DiscountItem.of(dDayEvent.getDiscountType(), orderDate.getDate()));
        }
    }

    public String toString(Integer countMatchMenuDessert, Integer countMatchMenuMain, Integer date) {
        if (isActive()) {
            String result = String.join(
                    OutputMessage.LINE_FEED,
                    List.of(
                                    dDayEvent.toString(date),
                                    weekdayEvent.toString(countMatchMenuDessert),
                                    weekendEvent.toString(countMatchMenuMain),
                                    specialEvent.toString(date),
                                    presentationEvent.toString()
                            ).stream()
                            .filter(str -> !str.isBlank())
                            .toList()
            );

            if (!result.isBlank()) {
                return result;
            }
        }

        return OutputMessage.NOTHING;
    }
}
