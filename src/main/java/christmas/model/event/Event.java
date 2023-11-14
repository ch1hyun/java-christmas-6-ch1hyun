package christmas.model.event;

import christmas.constants.OutputMessage;
import java.util.Arrays;
import java.util.List;

public class Event {
    private final DDayEvent dDayEvent;
    private final WeekdayEvent weekdayEvent;
    private final WeekendEvent weekendEvent;
    private final SpecialEvent specialEvent;
    private final PresentationEvent presentationEvent;

    private Event(DDayEvent dDayEvent, WeekdayEvent weekdayEvent, WeekendEvent weekendEvent, SpecialEvent specialEvent,
                 PresentationEvent presentationEvent) {
        this.dDayEvent = dDayEvent;
        this.weekdayEvent = weekdayEvent;
        this.weekendEvent = weekendEvent;
        this.specialEvent = specialEvent;
        this.presentationEvent = presentationEvent;
    }

    public static Event of(DDayEvent dDayEvent, WeekdayEvent weekdayEvent, WeekendEvent weekendEvent, SpecialEvent specialEvent,
                           PresentationEvent presentationEvent) {
        return new Event(
                dDayEvent,
                weekdayEvent,
                weekendEvent,
                specialEvent,
                presentationEvent
        );
    }

    public String toString(Integer date) {
        return String.join(
                OutputMessage.LINE_FEED,
                List.of(
                    dDayEvent.toString(date),
                    weekdayEvent.toString(date),
                    weekendEvent.toString(date),
                    specialEvent.toString(date),
                    presentationEvent.toString()
            )
        );
    }
}
