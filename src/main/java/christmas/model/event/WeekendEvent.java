package christmas.model.event;

import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.model.OrderDate;
import christmas.util.Formatter;

public class WeekendEvent {
    private static final String EVENT_NAME = "주말 할인";
    private final Activation activation;
    private final DiscountType discountType;

    private WeekendEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.주말;
    }

    public static WeekendEvent from(OrderDate orderDate) {
        Boolean active = false;

        if (orderDate.isDayGroup(DiscountType.주말)) {
            active = true;
        }

        return new WeekendEvent(Activation.from(active));
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public Boolean isAcitve() {
        return activation.isActive();
    }

    public String toString(Integer date) {
        if (isAcitve()) {
            return Formatter.formatRewardItem(
                    EVENT_NAME,
                    -discountType.getAmount(date)
            );
        }

        return "";
    }
}
