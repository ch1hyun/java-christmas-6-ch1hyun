package christmas.model.event;

import christmas.constants.EventConstant;
import christmas.constants.OutputMessage;
import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.model.OrderDate;
import christmas.util.Formatter;

public class WeekendEvent {
    private final Activation activation;
    private final DiscountType discountType;

    private WeekendEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.주말;
    }

    public static WeekendEvent from(OrderDate orderDate) {
        Boolean active = EventConstant.FALSE;

        if (orderDate.isDayGroup(DiscountType.주말)) {
            active = EventConstant.TRUE;
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
                    EventConstant.WEEKEND_DISCOUNT_NAME,
                    -discountType.getAmount(date)
            );
        }

        return OutputMessage.EMPTY;
    }
}
