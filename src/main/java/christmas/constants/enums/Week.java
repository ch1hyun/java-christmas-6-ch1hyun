package christmas.constants.enums;

import java.util.Arrays;
import java.util.List;

public enum Week {
    평일(Arrays.asList(Day.월, Day.화, Day.수, Day.목, Day.금)),
    주말(Arrays.asList(Day.토, Day.일)),
    특별(Arrays.asList(Day.일, Day.크리스마스)),
    디데이(Arrays.asList(Day.첫날, Day.크리스마스));

    private final List<Day> dayList;

    Week(List<Day> dayList) {
        this.dayList = dayList;
    }

    public Boolean contains(Day day) {
        return dayList.contains(day);
    }

    public Boolean contains(Integer date) {
        return dayList.get(0).getIndex() <= date
                && date <= dayList.get(1).getIndex();
    }
}
