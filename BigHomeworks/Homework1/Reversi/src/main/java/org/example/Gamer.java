package org.example;

import java.util.ArrayList;

public abstract class Gamer {
    public final String NAME;
    public final Color COLOR;
    public final boolean IS_COMPUTER;
    private int highScore = 0;

    protected int getHighScore() {
        return highScore;
    }

    protected void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    protected Gamer(String name, Color color, boolean isComputer) {
        NAME = name;
        COLOR = color;
        IS_COMPUTER = isComputer;
    }

    public abstract String makeMove(ArrayList<String> moves);

}
