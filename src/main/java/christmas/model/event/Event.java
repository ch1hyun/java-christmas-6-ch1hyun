package christmas.model.event;

import christmas.constants.OutputMessage;
import christmas.model.OrderDate;
import christmas.model.OrderList;
import java.util.Arrays;
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

    public static Event of(Integer subTotal, DDayEvent dDayEvent, WeekdayEvent weekdayEvent, WeekendEvent weekendEvent, SpecialEvent specialEvent,
                           PresentationEvent presentationEvent) {
        Boolean active = false;

        if (isSubTotalGreaterThanCriteria(subTotal)) {
            active = true;
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

    private static Boolean isSubTotalGreaterThanCriteria(Integer subTotal) {
        return subTotal >= 10000;
    }

    public Boolean isActive() {
        return activation.isActive();
    }

    public String toString(Integer countMatchMenuDessert, Integer countMatchMenuMain, Integer date) {

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

        if (result.isBlank()) {
            return OutputMessage.NOTHING;
        }

        return result;
    }
}
