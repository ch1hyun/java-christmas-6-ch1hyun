package christmas.model;

import christmas.util.Formatter;

public class Cost {
    private final Integer subTotal;

    private Cost(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public static Cost from(Integer subTotal) {
        return new Cost(subTotal);
    }

    public Boolean isGreaterThanOrEqualTo(Integer amount) {
        return subTotal >= amount;
    }

    @Override
    public String toString() {
        return Formatter.formatAmount(subTotal);
    }
}
