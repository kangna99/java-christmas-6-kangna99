package christmas.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveawayEventTest {
    private GiveawayEvent event;

    @BeforeEach
    void setUp() {
        event = new GiveawayEvent();
    }

    @Test
    @DisplayName("이벤트 혜택 금액 확인")
    void calculateBenefitAmount() {
        assertEquals(Menu.CHAMPAGNE.getPrice(), event.calculateBenefitAmount(120_000));
        assertEquals(0, event.calculateBenefitAmount(110_000));
    }

    @Test
    @DisplayName("이벤트 이름 확인")
    void eventName() {
        assertEquals("증정 이벤트", event.eventName());
    }

    @Test
    @DisplayName("증정 아이템 확인")
    void item() {
        assertEquals(Menu.CHAMPAGNE, event.item());
    }

    @Test
    @DisplayName("증정 아이템 수량 확인")
    void itemCount() {
        assertEquals(1, event.itemCount());
    }
}