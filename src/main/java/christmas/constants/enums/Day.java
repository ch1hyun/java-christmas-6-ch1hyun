package christmas.constants.enums;

import java.util.Arrays;

public enum Day {
    월(1),
    화(2),
    수(3),
    목(4),
    금(5),
    토(6),
    일(7),
    첫날(1),
    크리스마스(25);

    private final Integer index;

    Day(Integer index) {
        this.index = index;
    }

    public static Day from(Integer index) {
        return Arrays.stream(Day.values())
                .filter(day -> day.index.equals(index))
                .toList()
                .get(0);
    }

    public Integer getIndex() {
        return index;
    }
}
