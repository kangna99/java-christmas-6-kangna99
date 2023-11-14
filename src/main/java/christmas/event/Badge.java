package christmas.event;

public enum Badge {
    NONE("없음"),
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String name;

    Badge(String name) {
        this.name = name;
    }

    public static Badge selectBadge(int totalBenefitPrice) {
        if (totalBenefitPrice >= 20_000) {
            return SANTA;
        }
        if (totalBenefitPrice >= 10_000) {
            return TREE;
        }
        if (totalBenefitPrice >= 5_000) {
            return STAR;
        }
        return NONE;

    }

    public String getName() {
        return name;
    }
}