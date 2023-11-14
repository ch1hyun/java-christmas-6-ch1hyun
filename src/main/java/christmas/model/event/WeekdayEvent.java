package christmas.model.event;

import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.util.Formatter;

public class WeekdayEvent {
    private static final String EVENT_NAME = "평일 할인";
    private final Activation activation;
    private final DiscountType discountType;

    private WeekdayEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.평일;
    }

    public static WeekdayEvent from(Day day) {
        Boolean active = false;

        if (DiscountType.평일.contains(day)) {
            active = true;
        }

        return new WeekdayEvent(Activation.from(active));
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
