package org.example;

/**
 * Класс пункта меню.
 */
public final class MenuItem {
    /**
     * Длина пункта меню.
     */
    private int length = 50;
    /**
     * Верхний текст пункта меню.
     */
    private String title = "";
    /**
     * Нижний текст пункта меню.
     */
    private String subtitle = "";

    /**
     * Устанавливает длину пункта меню.
     * @param length длина пункта меню.
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Устанавливает верхний текст пункта меню.
     * @param title верхний текст
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Устанавливает нижний текст пункта меню.
     * @param subtitle нижний текст
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * Выводит пункт меню.
     */
    public void show() {
        for (int i = 0; i < length; ++i) {
            System.out.print("-");
        }
        System.out.println();

        System.out.print("|");
        for (int i = 1; i < (length - title.length()) / 2; ++i) {
            System.out.print(" ");
        }
        System.out.print(title);
        for (int i = 1; i < length - (length - title.length()) / 2 - title.length(); ++i) {
            System.out.print(" ");
        }
        System.out.println("|");

        System.out.print("|");
        for (int i = 1; i < (length - subtitle.length()) / 2; ++i) {
            System.out.print(" ");
        }
        System.out.print(subtitle);
        for (int i = 1; i < length - (length - subtitle.length()) / 2 - subtitle.length(); ++i) {
            System.out.print(" ");
        }
        System.out.println("|");

        for (int i = 0; i < length; ++i) {
            System.out.print("-");
        }
        System.out.println();
    }
}
