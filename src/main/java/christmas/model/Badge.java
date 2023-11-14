package christmas.model;

import christmas.constants.enums.BadgeType;

public class Badge {
    private final BadgeType badgeType;

    private Badge(BadgeType badgeType) {
        this.badgeType = badgeType;
    }

    public static Badge from(RewardAmount rewardAmount) {
        return new Badge(BadgeType.getBadgeType(rewardAmount));
    }

    @Override
    public String toString() {
        return badgeType.getName();
    }
}
