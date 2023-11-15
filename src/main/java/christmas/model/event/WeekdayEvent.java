package christmas.model.event;

import christmas.constants.EventConstant;
import christmas.constants.enums.DiscountType;
import christmas.model.OrderDate;
import christmas.util.Formatter;

public class WeekdayEvent {
    private final Activation activation;
    private final DiscountType discountType;

    private WeekdayEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.평일;
    }

    public static WeekdayEvent from(OrderDate orderDate) {
        Boolean active = EventConstant.FALSE;

        if (orderDate.isDayGroup(DiscountType.평일)) {
            active = EventConstant.TRUE;
        }

        return new WeekdayEvent(Activation.from(active));
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
                    EventConstant.WEEKDAY_DISCOUNT_NAME,
                    -discountType.getAmount(date)
            );
        }

        return "";
    }
}
