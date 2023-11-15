package christmas.constants.enums;

public enum DiscountType {
    디데이("크리스마스 디데이 할인", Week.디데이, 1_000, 100), // 기간, 시작 금액, 증가 금액
    평일("평일 할인", Week.평일, 2_023, 2_023),
    주말("주말 할인", Week.주말, 2_023, 2_023),
    특별("특별 할인", Week.특별, 1_000, 0);

    private final String name;
    private final Week week;
    private final Integer startAmount;
    private final Integer offsetAmount;

    DiscountType(String name, Week week, Integer startAmount, Integer offsetAmount) {
        this.name = name;
        this.week = week;
        this.startAmount = startAmount;
        this.offsetAmount = offsetAmount;
    }

    public Boolean contains(Day day) {
        return week.contains(day);
    }
    public Boolean contains(Integer date) {
        return week.contains(date);
    }

    public String getName() {
        return name;
    }

    public Integer getStartAmount() {
        return startAmount;
    }

    public Integer getOffsetAmount() {
        return offsetAmount;
    }

    public Integer getAmount(Integer number) {
        return getStartAmount() + getOffsetAmount() * (number - 1);
    }
}
