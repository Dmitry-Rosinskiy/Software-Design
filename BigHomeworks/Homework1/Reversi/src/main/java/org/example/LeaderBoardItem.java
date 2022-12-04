package org.example;

public class LeaderBoardItem {
    private int nameLength = 100;
    private int scoreLength = 10;
    private boolean isLast = false;
    private String name = "";
    private int score = 0;

    public void setNameLength(int length) {
        nameLength = length;
    }

    public void setScoreLength(int length) {
        scoreLength = length;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void show() {
        for (int i = 0; i < nameLength + scoreLength - 1; ++i) {
            System.out.print("-");
        }
        System.out.println();

        System.out.print("|");
        for (int i = 1; i < (nameLength - name.length()) / 2; ++i) {
            System.out.print(" ");
        }
        System.out.print(name);
        for (int i = 1; i < nameLength - (nameLength - name.length()) / 2 - name.length(); ++i) {
            System.out.print(" ");
        }
        System.out.print("|");
        for (int i = 1; i < (scoreLength - (score + "").length()) / 2; ++i) {
            System.out.print(" ");
        }
        System.out.print(score);
        for (int i = 1; i < scoreLength - (scoreLength - (score + "").length()) / 2 - (score + " ").length() + 1; ++i) {
            System.out.print(" ");
        }
        System.out.println("|");

        if (isLast) {
            for (int i = 0; i < nameLength + scoreLength - 1; ++i) {
                System.out.print("-");
            }
            System.out.println();
        }
    }
}
