package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс игрока.
 */
public final class Player extends Gamer {

    /**
     * Конструктор игрока по имени и игровому цвету.
     * @param name имя
     * @param color цвет
     */
    public Player(String name, Color color) {
        super(name, color, false);
    }

    /**
     * Делает ход.
     * @param moves список возможных ходов
     * @return выбранный ход
     */
    @Override
    public String makeMove(ArrayList<String> moves) {
        Scanner in = new Scanner(System.in);
        String move = in.nextLine();
        while (!moves.contains(move)) {
            System.out.println("Такой ход невозможен!");
            move = in.nextLine();
        }
        return move;
    }
}
