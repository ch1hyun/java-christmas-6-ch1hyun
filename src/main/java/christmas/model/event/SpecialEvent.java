package christmas.model.event;

import christmas.constants.EventConstant;
import christmas.constants.OutputMessage;
import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.model.OrderDate;
import christmas.util.Formatter;

public class SpecialEvent {
    private final Activation activation;
    private final DiscountType discountType;

    private SpecialEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.특별;
    }

    public static SpecialEvent from(OrderDate orderDate) {
        Boolean active = EventConstant.FALSE;

        if (orderDate.isDayGroup(DiscountType.특별)) {
            active = EventConstant.TRUE;
        }

        return new SpecialEvent(Activation.from(active));
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
                    EventConstant.SPECIAL_DISCOUNT_NAME,
                    -discountType.getAmount(date)
            );
        }

        return OutputMessage.EMPTY;
    }
}
