package christmas.model.event;

import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.util.Formatter;

public class DDayEvent {
    private static final String EVENT_NAME = "크리스마스 디데이 할인";
    private final Activation activation;
    private final DiscountType discountType;

    private DDayEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.디데이;
    }

    public static DDayEvent from(Integer date) {
        Boolean active = false;

        if (DiscountType.디데이.contains(date)) {
            active = true;
        }

        return new DDayEvent(Activation.from(active));
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
