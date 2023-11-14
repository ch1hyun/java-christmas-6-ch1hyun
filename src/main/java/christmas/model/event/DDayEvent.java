package christmas.model.event;

import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.model.OrderDate;
import christmas.util.Formatter;

public class DDayEvent {
    private static final String EVENT_NAME = "크리스마스 디데이 할인";
    private final Activation activation;
    private final DiscountType discountType;

    private DDayEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.디데이;
    }

    public static DDayEvent from(OrderDate orderDate) {
        Boolean active = false;

        if (orderDate.isDateGroup(DiscountType.디데이)) {
            active = true;
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
                    EVENT_NAME,
                    -discountType.getAmount(date)
            );
        }

        return "";
    }
}
