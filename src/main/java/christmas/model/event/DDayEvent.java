package christmas.model.event;

import christmas.constants.EventConstant;
import christmas.constants.enums.DiscountType;
import christmas.model.OrderDate;
import christmas.util.Formatter;

public class DDayEvent {
    private final Activation activation;
    private final DiscountType discountType;

    private DDayEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.디데이;
    }

    public static DDayEvent from(OrderDate orderDate) {
        Boolean active = EventConstant.FALSE;

        if (orderDate.isDateGroup(DiscountType.디데이)) {
            active = EventConstant.TRUE;
        }

        return new DDayEvent(Activation.from(active));
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
                    EventConstant.DDAY_DISCOUNT_NAME,
                    -discountType.getAmount(date)
            );
        }

        return "";
    }
}
