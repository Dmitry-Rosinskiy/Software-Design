package org.example;

import java.util.ArrayList;

/**
 * Класс абстрактного игрока.
 */
public abstract class Gamer {
    /**
     * Имя игрока.
     */
    public final String NAME;
    /**
     * Игровой цвет игрока.
     */
    public final Color COLOR;
    /**
     * Игрок является компьютером.
     */
    public final boolean IS_COMPUTER;
    /**
     * Лучший результат игрока.
     */
    private int highScore = 0;

    /**
     * Возвращает лучший результат игрока.
     * @return лучший результат игрока
     */
    protected int getHighScore() {
        return highScore;
    }

    /**
     * Устанавливает лучший результат игрока
     * @param highScore лучший результат игрока
     */
    protected void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * Конструктор игрока по имени, игровому цвету и принадлежностью к компьютеру.
     * @param name имя
     * @param color цвет
     * @param isComputer является компьютером
     */
    protected Gamer(String name, Color color, boolean isComputer) {
        NAME = name;
        COLOR = color;
        IS_COMPUTER = isComputer;
    }

    /**
     * Делает ход.
     * @param moves список возможных ходов
     * @return выбранный ход
     */
    public abstract String makeMove(ArrayList<String> moves);

}
