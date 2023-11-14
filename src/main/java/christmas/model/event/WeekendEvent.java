package christmas.model.event;

import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.util.Formatter;

public class WeekendEvent {
    private static final성 String EVENT_NAME = "주말 할인";
    private final Activation activation;
    private final DiscountType discountType;

    private WeekendEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.주말;
    }

    public static WeekendEvent from(Day day) {
        Boolean active = false;

        if (DiscountType.주말.contains(day)) {
            active = true;
        }

        return new WeekendEvent(Activation.from(active));
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
