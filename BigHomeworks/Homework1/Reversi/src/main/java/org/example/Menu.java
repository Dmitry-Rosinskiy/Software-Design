package org.example;

import java.util.ArrayList;

public class Menu {

    private int length = 30;
    private final ArrayList<MenuItem> MENU_ITEM_LIST = new ArrayList<>();
    private String title = "";

    public void setLength(int length) {
        this.length = length;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void show() {
        System.out.println("\t\t\t" + title);
        System.out.println();
        for (MenuItem item : MENU_ITEM_LIST) {
            item.show();
        }
    }

    public void addMenuItem(String title, String subtitle) {
        var menuItem = new MenuItem();
        menuItem.setTitle(title);
        menuItem.setSubtitle(subtitle);
        menuItem.setLength(length);
        MENU_ITEM_LIST.add(menuItem);
    }
}
