package christmas.model;

import christmas.constants.enums.ErrorMessage;
import christmas.util.Validator;

public class Count {
    private final Integer count;

    private Count(Integer count) {
        validate(count);

        this.count = count;
    }

    private void validate(Integer count) {
        Validator.validatePositiveNumber(count, ErrorMessage.INVALID_ORDER.getMessage());
    }

    public static Count from(Integer count) {
        return new Count(count);
    }

    public Integer getCount() {
        return count;
    }
}
