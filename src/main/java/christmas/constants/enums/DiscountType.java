package christmas.constants.enums;

public enum DiscountType {
    디데이(Week.디데이, 1_000, 100), // 기간, 시작 금액, 증가 금액
    평일(Week.평일, 2_023, 2_023),
    주말(Week.주말, 2_023, 2_023),
    특별(Week.특별, 1_000, 0);

    private final Week week;
    private final Integer startAmount;
    private final Integer offsetAmount;

    DiscountType(Week week, Integer startAmount, Integer offsetAmount) {
        this.week = week;
        this.startAmount = startAmount;
        this.offsetAmount = offsetAmount;
    }

    public Boolean contains(Day day) {
        return week.contains(day);
    }

    public Integer getStartAmount() {
        return startAmount;
    }

    public Integer getOffsetAmount() {
        return offsetAmount;
    }
}
