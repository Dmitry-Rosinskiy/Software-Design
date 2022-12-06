package org.example;

/**
 * Класс возможного хода.
 */
public final class PossibleMove implements HasSymbol {
    /**
     * Возвращает символьное представление хода.
     * @return символьное представление хода
     */
    @Override
    public char getSymbol() {
        return '✘';
    }
}
