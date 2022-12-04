package org.example;

public class Disk implements HasSymbol {
    private Color color;

    public Color getColor() {
        return color;
    }

    Disk(Color color) {
        this.color = color;
    }

    public void changeColor() {
        if (color == Color.BLACK) {
            color = Color.WHITE;
        } else {
            color = Color.BLACK;
        }
    }

    @Override
    public char getSymbol() {
        if (color == Color.BLACK) {
            return '●';
        } else {
            return '○';
        }
    }
}
