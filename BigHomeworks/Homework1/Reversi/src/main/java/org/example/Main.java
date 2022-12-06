package org.example;

/**
 * Основной класс программы.
 */
public class Main {
    /**
     * Точка входа в программу.
     * @param args входные аргументы
     */
    public static void main(String[] args) {
        var game = new Reversi();
        game.start();
    }
}
