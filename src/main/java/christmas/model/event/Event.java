package christmas.model.event;

import christmas.constants.OutputMessage;
import christmas.model.OrderAmount;
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
        Boolean active = false;

        if (orderAmount.isGreaterThanOrEqualTo(10000)) {
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
