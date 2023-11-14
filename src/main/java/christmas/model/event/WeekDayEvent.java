package christmas.model.event;

import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.util.Formatter;

public class WeekDayEvent {
    private static final String EVENT_NAME = "평일 할인";
    private final Activation activation;
    private final DiscountType discountType;

    private WeekDayEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.평일;
    }

    public static WeekDayEvent from(Day day) {
        Boolean active = false;

        if (DiscountType.평일.contains(day)) {
            active = true;
        }

        return new WeekDayEvent(Activation.from(active));
    }

    public Boolean isAcitve() {
        return activation.isActive();
    }

    public String toString(Integer date) {
        return Formatter.formatRewardItem(
                EVENT_NAME,
                -discountType.getAmount(date)
        );
    }
}
