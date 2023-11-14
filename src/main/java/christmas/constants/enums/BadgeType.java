package christmas.constants.enums;

import java.util.Arrays;
import java.util.Comparator;

public enum BadgeType {
    NULL("없음", 0),
    별("별", 5_000),
    트리("트리", 10_000),
    산타("산타", 20_000);

    private final String name;
    private final Integer minimumAmount;

    BadgeType(String name, Integer minimumAmount) {
        this.name = name;
        this.minimumAmount = minimumAmount;
    }

    public String getName() {
        return name;
    }

    public BadgeType getBadgeType(Integer rewardAmount) {
        if (rewardAmount >= BadgeType.산타.minimumAmount) {
            return BadgeType.산타;
        }

        if (rewardAmount >= BadgeType.트리.minimumAmount) {
            return BadgeType.트리;
        }

        if (rewardAmount >= BadgeType.별.minimumAmount) {
            return BadgeType.별;
        }

        return BadgeType.NULL;
    }
}
