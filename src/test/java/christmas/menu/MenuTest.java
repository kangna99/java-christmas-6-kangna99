package christmas.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {
    @Test
    @DisplayName("메뉴 이름 확인")
    void contains() {
        assertTrue(Menu.contains("양송이수프"));
        assertTrue(Menu.contains("타파스"));
        assertTrue(Menu.contains("시저샐러드"));
        assertTrue(Menu.contains("티본스테이크"));
        assertTrue(Menu.contains("바비큐립"));
        assertTrue(Menu.contains("해산물파스타"));
        assertTrue(Menu.contains("크리스마스파스타"));
        assertTrue(Menu.contains("초코케이크"));
        assertTrue(Menu.contains("아이스크림"));
        assertTrue(Menu.contains("제로콜라"));
        assertTrue(Menu.contains("레드와인"));
        assertTrue(Menu.contains("샴페인"));
        assertFalse(Menu.contains("파스타"));
    }

    @Test
    @DisplayName("메뉴 이름으로 메뉴 상수 반환")
    void called() {
        assertEquals(Menu.CHOCOLATE_CAKE, Menu.called("초코케이크"));
        assertEquals(Menu.T_BONE_STEAK, Menu.called("티본스테이크"));
        assertThrows(IllegalArgumentException.class, () -> Menu.called("파스타"));
    }

    @Test
    @DisplayName("메뉴 속성 확인")
    void menuProperties() {
        assertEquals("아이스크림", Menu.ICE_CREAM.getName());
        assertEquals(5_000, Menu.ICE_CREAM.getPrice());
        assertEquals(Category.DESSERT, Menu.ICE_CREAM.getCategory());

        assertEquals("샴페인", Menu.CHAMPAGNE.getName());
        assertEquals(25_000, Menu.CHAMPAGNE.getPrice());
        assertEquals(Category.BEVERAGE, Menu.CHAMPAGNE.getCategory());
    }
}