package christmas.model.event;

import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.model.OrderDate;
import christmas.util.Formatter;

public class SpecialEvent {
    private static final String EVENT_NAME = "특별 할인";
    private final Activation activation;
    private final DiscountType discountType;

    private SpecialEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.특별;
    }

    public static SpecialEvent from(OrderDate orderDate) {
        Boolean active = false;

        if (orderDate.isDayGroup(DiscountType.특별)) {
            active = true;
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
                    EVENT_NAME,
                    -discountType.getAmount(date)
            );
        }

        return "";
    }
}
