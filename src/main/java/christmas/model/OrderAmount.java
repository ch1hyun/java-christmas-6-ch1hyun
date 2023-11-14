package christmas.model;

import christmas.constants.enums.ErrorMessage;
import christmas.util.Formatter;
import christmas.util.Validator;

public class OrderAmount {
    private final Integer subTotal;

    private OrderAmount(Integer subTotal) {
        validate(subTotal);

        this.subTotal = subTotal;
    }

    private void validate(Integer subTotal) {
        Validator.validatePositiveNumber(subTotal, ErrorMessage.INVALID_ORDER.getMessage());
    }

    public static OrderAmount from(Integer subTotal) {
        return new OrderAmount(subTotal);
    }

    public Boolean isGreaterThanOrEqualTo(Integer amount) {
        return subTotal >= amount;
    }

    public Integer getTotalAmount(DiscountList discountList) {
        return subTotal - discountList.getAmount();
    }

    @Override
    public String toString() {
        return Formatter.formatAmount(subTotal);
    }
}
