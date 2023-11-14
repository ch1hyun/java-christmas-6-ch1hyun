package christmas.model.event;

import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.util.Formatter;

public class SpecialEvent {
    private static final String EVENT_NAME = "특별 할인";
    private final Activation activation;
    private final DiscountType discountType;

    private SpecialEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.특별;
    }

    public static SpecialEvent from(Day day) {
        Boolean active = false;

        if (DiscountType.특별.contains(day)) {
            active = true;
        }

        return new SpecialEvent(Activation.from(active));
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
