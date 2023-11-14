package christmas.model;

import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class OrderDate {
    private final Integer date;
    private final Day day;

    private OrderDate(Integer date, Day day) {
        this.date = date;
        this.day = day;
    }

    public static OrderDate from(Integer date) {
        // validate
        LocalDate localDate = LocalDate.of(2023, 12, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        return new OrderDate(date, Day.from(dayOfWeek.getValue()));
    }

    public Boolean isGroup(DiscountType discountType) {
        return discountType.contains(day);
    }
}
