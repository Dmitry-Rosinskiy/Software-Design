package org.example;

import java.util.ArrayList;

/**
 * Клаас элемента меню.
 */
public final class Menu {

    /**
     * Длина меню.
     */
    private int length = 30;
    /**
     * Список пунктов меню.
     */
    private final ArrayList<MenuItem> MENU_ITEM_LIST = new ArrayList<>();
    /**
     * Заголовок меню.
     */
    private String title = "";

    /**
     * Устанавливает длину меню.
     * @param length длина меню
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Устанавливает заголовок меню.
     * @param title заголовок меню
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Выводит меню.
     */
    public void show() {
        System.out.println("\t\t\t" + title);
        System.out.println();
        for (MenuItem item : MENU_ITEM_LIST) {
            item.show();
        }
    }

    /**
     * Добавляет пункт меню.
     * @param title верхний текст меню
     * @param subtitle нижний текст меню
     */
    public void addMenuItem(String title, String subtitle) {
        var menuItem = new MenuItem();
        menuItem.setTitle(title);
        menuItem.setSubtitle(subtitle);
        menuItem.setLength(length);
        MENU_ITEM_LIST.add(menuItem);
    }
}
