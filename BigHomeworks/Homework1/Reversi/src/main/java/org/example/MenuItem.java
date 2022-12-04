package org.example;

public class MenuItem {
    private int length = 50;
    private String title = "";
    private String subtitle = "";

    public void setLength(int length) {
        this.length = length;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

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
