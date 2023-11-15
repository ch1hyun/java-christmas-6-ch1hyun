package christmas.constants.enums;

import christmas.constants.EventConstant;
import java.util.Arrays;
import java.util.List;

public enum Week {
    평일(Arrays.asList(Day.월, Day.화, Day.수, Day.목, Day.금)){
        public Boolean contains(Integer date) {
            return EventConstant.FALSE;
        }
    },
    주말(Arrays.asList(Day.토, Day.일)){
        public Boolean contains(Integer date) {
            return EventConstant.FALSE;
        }
    },
    특별(Arrays.asList(Day.일)) {
        public Boolean contains(Integer date) {
            return date.equals(Day.크리스마스.getIndex());
        }
    },
    디데이(Arrays.asList()){
        public Boolean contains(Integer date) {
            return Day.첫날.getIndex() <= date && date <= Day.크리스마스.getIndex();
        }
    };

    private final List<Day> dayList;

    Week(List<Day> dayList) {
        this.dayList = dayList;
    }

    public Boolean contains(Day day) {
        return dayList.contains(day);
    }

    public abstract Boolean contains(Integer date);
}
