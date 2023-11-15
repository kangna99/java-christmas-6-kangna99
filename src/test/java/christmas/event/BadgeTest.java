package christmas.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {
    @Test
    @DisplayName("금액에 따라 올바른 배지를 선택한다.")
    void selectBadge() {
        assertEquals(Badge.SANTA, Badge.selectBadge(22_023));
        assertEquals(Badge.TREE, Badge.selectBadge(15_000));
        assertEquals(Badge.STAR, Badge.selectBadge(6_666));
        assertEquals(Badge.NONE, Badge.selectBadge(1_000));
    }

    @Test
    @DisplayName("각 배지의 이름을 반환한다.")
    void getName() {
        assertEquals("산타", Badge.SANTA.getName());
        assertEquals("트리", Badge.TREE.getName());
        assertEquals("별", Badge.STAR.getName());
        assertEquals("없음", Badge.NONE.getName());
    }
}