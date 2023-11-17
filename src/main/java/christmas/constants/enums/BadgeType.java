package christmas.constants.enums;

import christmas.model.RewardAmount;

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

    public static BadgeType getBadgeType(RewardAmount rewardAmount) {
        if (rewardAmount.isGreaterThanOrEqualTo(BadgeType.산타.minimumAmount)) {
            return BadgeType.산타;
        }

        if (rewardAmount.isGreaterThanOrEqualTo(BadgeType.트리.minimumAmount)) {
            return BadgeType.트리;
        }

        if (rewardAmount.isGreaterThanOrEqualTo(BadgeType.별.minimumAmount)) {
            return BadgeType.별;
        }

        return BadgeType.NULL;
    }
}
