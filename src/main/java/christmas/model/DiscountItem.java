package christmas.model;

import christmas.constants.enums.DiscountType;
import christmas.util.Formatter;

public class DiscountItem {
    private final DiscountType discountType;
    private final Integer amount;

    private DiscountItem(DiscountType discountType, Integer amount) {
        this.discountType = discountType;
        this.amount = amount;
    }

    public static DiscountItem of(DiscountType discountType, Integer date) {
        return new DiscountItem(discountType, discountType.getAmount(date));
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return Formatter.formatRewardItem(
                discountType.getName(),
                -amount
        );
    }
}
