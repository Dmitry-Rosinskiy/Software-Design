package org.example;

/**
 * Класс фишки.
 */
public final class Disk implements HasSymbol {
    /**
     * Цвет фишки.
     */
    private Color color;

    /**
     * Возвращает цвет фишки.
     * @return цвет фишки
     */
    public Color getColor() {
        return color;
    }

    /**
     * Конструктор фишки по цвету.
     * @param color цвет
     */
    Disk(Color color) {
        this.color = color;
    }

    /**
     * Меняет цвет фишки на противоположный.
     */
    public void changeColor() {
        if (color == Color.BLACK) {
            color = Color.WHITE;
        } else {
            color = Color.BLACK;
        }
    }

    /**
     * Возвращает символьное представление фишки.
     * @return симольное представление фишки
     */
    @Override
    public char getSymbol() {
        if (color == Color.BLACK) {
            return '●';
        } else {
            return '○';
        }
    }
}
