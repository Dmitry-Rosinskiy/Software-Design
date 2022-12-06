package org.example;

/**
 * Класс конвертера.
 */
public final class Converter {
    /**
     * Преобразует координаты в позицию.
     * @param row индекс строки
     * @param col индекс столбца
     * @return строковое представление позиции
     */
    public static String convertToPosition(int row, int col) {
        return "" + (char) ('a' + col) + (row + 1);
    }

    /**
     * Получает из строкового представления позиции координату по строке.
     * @param pos строковое представление позиции
     * @return индекс строки
     */
    public static int convertToRow(String pos) {
        return pos.charAt(1) - '0' - 1;
    }

    /**
     * Получает из строкового представления позиции координату по столбцу.
     * @param pos строковое представление позиции
     * @return индекс столбца
     */
    public static int convertToCol(String pos) {
        return pos.charAt(0) - 'a';
    }
}
