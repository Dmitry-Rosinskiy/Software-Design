package org.example;

public final class Board {
    public final int SIZE;
    private final HasSymbol[][] OBJECTS_MATRIX;

    Board(int size) {
        SIZE = size;
        OBJECTS_MATRIX = new HasSymbol[SIZE][SIZE];
    }

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

    public HasSymbol getObject(int row, int col) {
        return OBJECTS_MATRIX[row][col];
    }

    public void setObject(HasSymbol object, int row, int col) {
        OBJECTS_MATRIX[row][col] = object;
    }

    public void removeObject(int row, int col) {
        OBJECTS_MATRIX[row][col] = null;
    }

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

    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public boolean isCornerCell(int row, int col) {
        return (row == 0 && col == 0) ||
                (row == 0 && col == SIZE - 1) ||
                (row == SIZE - 1 && col == 0) ||
                (row == SIZE - 1 && col == SIZE - 1);
    }

    public boolean isEdgeCell(int row, int col) {
        return (row == 0 && col > 0 && col < SIZE - 1) ||
                (row == SIZE - 1 && col > 0 && col < SIZE - 1) ||
                (row > 0 && row < SIZE - 1 && col == 0) ||
                (row > 0 && row < SIZE - 1 && col == SIZE - 1);
    }

    private void printLine() {
        for (int i = 0; i < 4 * SIZE; ++i) {
            System.out.print("-");
        }
        System.out.println("-");
    }

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
