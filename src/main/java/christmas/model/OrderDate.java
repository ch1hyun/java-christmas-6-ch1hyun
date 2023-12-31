package christmas.model;

import christmas.constants.EventConstant;
import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.constants.enums.ErrorMessage;
import christmas.util.Validator;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class OrderDate {
    private final Integer date;
    private final Day day;

    private OrderDate(Integer date, Day day) {
        this.date = date;
        this.day = day;
    }

    private static void validate(Integer date) {
        Validator.validateNumberInRange(1, 31, date, ErrorMessage.INVALID_DATE.getMessage());
    }

    public static OrderDate from(Integer date) {
        validate(date);

        LocalDate localDate = LocalDate.of(EventConstant.YEAR, EventConstant.MONTH, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        return new OrderDate(date, Day.from(dayOfWeek.getValue()));
    }

    public Integer getDate() {
        return date;
    }

    public Boolean isDayGroup(DiscountType discountType) {
        return discountType.contains(day);
    }
    public Boolean isDateGroup(DiscountType discountType) {
        return discountType.contains(date);
    }
}
