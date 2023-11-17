package christmas.event;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String name;
    private final int benefitThreshold;

    Badge(String name, int benefitThreshold) {
        this.name = name;
        this.benefitThreshold = benefitThreshold;
    }

    public static Badge selectBadge(int totalBenefitPrice) {
        return Arrays.stream(values())
                .filter(badge -> totalBenefitPrice >= badge.benefitThreshold)
                .findFirst()
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}