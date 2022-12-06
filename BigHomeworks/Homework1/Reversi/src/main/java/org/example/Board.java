package org.example;

/**
 * Класс игровой доски.
 */
public final class Board {
    /**
     * Размер доски.
     */
    public final int SIZE;
    /**
     * Матрица объектов доски.
     */
    private final HasSymbol[][] OBJECTS_MATRIX;

    /**
     * Конструктор доски по её размеру.
     * @param size размер доски
     */
    Board(int size) {
        SIZE = size;
        OBJECTS_MATRIX = new HasSymbol[SIZE][SIZE];
    }

    /**
     * Выводит доску.
     */
    public void show() {
        char colID = 'a';
        System.out.print("  ");
        for (int i = 0; i < SIZE; ++i) {
            System.out.print("  ");
            System.out.print(colID++);
            System.out.print(" ");
        }
        System.out.println();

        int rowID = 1;
        for (int i = 0; i < SIZE; ++i) {
            System.out.print("  ");
            printLine();
            System.out.print(rowID++);
            System.out.print(" ");
            printRow(i);
        }
        System.out.print("  ");
        printLine();
    }

    /**
     * Возвращает объект по позиции.
     * @param row индекс строки
     * @param col индекс столбца
     * @return объект на позиции (row, col)
     */
    public HasSymbol getObject(int row, int col) {
        return OBJECTS_MATRIX[row][col];
    }

    /**
     * Установливает объект по позиции.
     * @param object объект
     * @param row индекс строки
     * @param col индекс столбца
     */
    public void setObject(HasSymbol object, int row, int col) {
        OBJECTS_MATRIX[row][col] = object;
    }

    /**
     * Удаляет объект по позиции
     * @param row индекс строки
     * @param col индекс столбца
     */
    public void removeObject(int row, int col) {
        OBJECTS_MATRIX[row][col] = null;
    }

    /**
     * Проверяет, есть ли на доске место для новых объектов.
     * @return true, если доска переполнена объектами, false в противном случае
     */
    public boolean isFull() {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if (OBJECTS_MATRIX[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Проверяет, существует ли ячейка с заданной позицией на доске.
     * @param row индекс строки
     * @param col индекс столбца
     * @return true, если ячейка существует, false в противном случае
     */
    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    /**
     * Проверяет, является ли ячейка с заданной позицией угловой.
     * @param row индекс строки
     * @param col индекс столбца
     * @return true, если ячейка угловая, false в противном случае
     */
    public boolean isCornerCell(int row, int col) {
        return (row == 0 && col == 0) ||
                (row == 0 && col == SIZE - 1) ||
                (row == SIZE - 1 && col == 0) ||
                (row == SIZE - 1 && col == SIZE - 1);
    }

    /**
     * Проверяет, является ли ячейка с заданной позицией кромочной.
     * @param row индекс строки
     * @param col индекс столбца
     * @return true, если ячейка кромочная, false в противном случае
     */
    public boolean isEdgeCell(int row, int col) {
        return (row == 0 && col > 0 && col < SIZE - 1) ||
                (row == SIZE - 1 && col > 0 && col < SIZE - 1) ||
                (row > 0 && row < SIZE - 1 && col == 0) ||
                (row > 0 && row < SIZE - 1 && col == SIZE - 1);
    }

    /**
     * Выводит линию.
     */
    private void printLine() {
        for (int i = 0; i < 4 * SIZE; ++i) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    /**
     * Выводит строку доски.
     * @param row индекс строки
     */
    private void printRow(int row) {
        for (int j = 0; j < SIZE; ++j) {
            System.out.print("| ");
            if (OBJECTS_MATRIX[row][j] != null) {
                System.out.print(OBJECTS_MATRIX[row][j].getSymbol());
            } else {
                System.out.print(" ");
            }
            System.out.print(" ");
        }
        System.out.println("|");
    }
}
