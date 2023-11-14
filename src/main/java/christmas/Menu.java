package christmas;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, Category.APPETIZER),
    TAPAS_PLATTER("타파스", 5_500, Category.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, Category.APPETIZER),
    T_BONE_STEAK("티본스테이크", 55_000, Category.MAIN_DISH),
    BARBEQUE_RIBS("바비큐립", 54_000, Category.MAIN_DISH),
    SEAFOOD_PASTA("해산물파스타", 35_000, Category.MAIN_DISH),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, Category.MAIN_DISH),
    CHOCOLATE_CAKE("초코케이크", 15_000, Category.DESSERT),
    ICE_CREAM("아이스크림", 5_000, Category.DESSERT),
    ZERO_COKE("제로콜라", 3_000, Category.BEVERAGE),
    RED_WINE("레드와인", 60_000, Category.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, Category.BEVERAGE);

    private final String name;
    private final int price;
    private final Category category;

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static boolean contains(String name) {
        return Arrays.stream(Menu.values())
                .anyMatch(menu -> menu.name.equalsIgnoreCase(name));
    }

//    public static Menu valueOf(String name) {
//        return Arrays.stream(Menu.values())
//                .filter(menu -> menu.name().equalsIgnoreCase(name))
//                .findFirst()
//                .orElseThrow(NoSuchElementException::new);
//    }

    public static Menu findMenu(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}