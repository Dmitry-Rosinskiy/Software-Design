package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public final class Player extends Gamer {

    public Player(String name, Color color) {
        super(name, color, false);
    }

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
