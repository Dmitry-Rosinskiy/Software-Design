package org.example;

/**
 * Класс пункта таблицы лучших результатов.
 */
public final class LeaderBoardItem {
    /**
     * Длина ячейки пункта таблицы для имени.
     */
    private int nameLength = 100;
    /**
     * Длина ячейки пункта таблицы для счёта.
     */
    private int scoreLength = 10;
    /**
     * Пункт таблицы является последним.
     */
    private boolean isLast = false;
    /**
     * Имя в пункте таблицы.
     */
    private String name = "";
    /**
     * Счёт в пункте таблицы.
     */
    private int score = 0;

    /**
     * Устанавливает длину ячейки пункта таблицы для имени.
     * @param length длина ячейки пункта таблицы
     */
    public void setNameLength(int length) {
        nameLength = length;
    }

    /**
     * Устанавливает длину ячейки пункта таблицы для счёта.
     * @param length длина ячейки пункта таблицы
     */
    public void setScoreLength(int length) {
        scoreLength = length;
    }

    /**
     * Устанавливает пункт таблицы последним.
     * @param isLast пункт таблицы является последним
     */
    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    /**
     * Устанавливает имя в пункте таблицы.
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает счёт в пункте таблицы.
     * @param score счёт
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Выводит пункт таблицы.
     */
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
