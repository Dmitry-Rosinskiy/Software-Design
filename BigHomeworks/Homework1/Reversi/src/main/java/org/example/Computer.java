package org.example;

import java.util.ArrayList;

/**
 * Класс компьютера.
 */
public final class Computer extends Gamer {
    /**
     * Игровая доска.
     */
    private Board board = null;

    /**
     * Конструктор компьютера по имени и цвету.
     * @param name имя компьютера
     * @param color игровой цвет
     */
    public Computer(String name, Color color) {
        super(name, color, true);
    }

    /**
     * Добавляет игровую доску.
     * @param board игровая доска
     */
    public void addBoard(Board board) {
        this.board = board;
    }

    /**
     * Делает ход.
     * @param moves список возможных ходов
     * @return выбранный ход
     */
    @Override
    public String makeMove(ArrayList<String> moves) {
        // System.out.println(NAME + " думает...");
        String bestMove = null;
        double maxPoints = 0;
        for (String move : moves) {
            int row = Converter.convertToRow(move);
            int col = Converter.convertToCol(move);
            double points = estimator(row, col);
            if (points > maxPoints) {
                bestMove = move;
                maxPoints = points;
            }
        }
        System.out.println(bestMove);
        return bestMove;
    }

    /**
     * Оценочная функция по позиции на игровой доске.
     * @param row индекс строки доски
     * @param col индекс столбца доски
     * @return значение оценочной функции
     */
    private double estimator(int row, int col) {
        double result = 0;
        double ssCorner = 0.8;
        double ssEdge = 0.4;
        double ssOther = 0;

        for (Direction direction : Direction.values()) {
            result += estimatorDirection(row, col, direction);
        }
        if (board.isCornerCell(row, col)) {
            result += ssCorner;
        } else if (board.isEdgeCell(row, col)) {
            result += ssEdge;
        } else {
            result += ssOther;
        }

        return result;
    }

    /**
     * Оценочная функция по позиции на игровой доске и направлению.
     * @param row индекс строки
     * @param col индекс столбца
     * @param direction направление
     * @return значение оценочной функции
     */
    private int estimatorDirection(int row, int col, Direction direction) {
        int result = 0;
        int sEdge = 2;
        int sOther = 1;

        row = advanceRowDirection(row, direction);
        col = advanceColDirection(col, direction);
        boolean foundOwnDisk = false;
        Color opponentColor = COLOR == Color.WHITE ? Color.BLACK : Color.WHITE;
        while (board.isValidCell(row, col)) {
            if (board.getObject(row, col) != null) {
                var disk = ((Disk) board.getObject(row, col));
                if (disk.getColor() == opponentColor) {
                    if (board.isEdgeCell(row, col)) {
                        result += sEdge;
                    } else {
                        result += sOther;
                    }
                } else {
                    foundOwnDisk = true;
                    break;
                }
            } else {
                break;
            }
            row = advanceRowDirection(row, direction);
            col = advanceColDirection(col, direction);
        }

        if (!foundOwnDisk) {
            return 0;
        }
        return result;
    }

    /**
     * Изменяет индекс строки согласно направлению.
     * @param row индекс строки
     * @param direction направление
     * @return новый индекс строки
     */
    private int advanceRowDirection(int row, Direction direction) {
        return switch (direction) {
            case UpLeft, Up, UpRight -> row - 1;
            case Left, Right -> row;
            case DownLeft, Down, DownRight -> row + 1;
        };
    }

    /**
     * Изменяет индекс столбца согласно направлению.
     * @param col индекс столбца
     * @param direction направление
     * @return новый индекс столбца
     */
    private int advanceColDirection(int col, Direction direction) {
        return switch (direction) {
            case UpLeft, Left, DownLeft -> col - 1;
            case Up, Down -> col;
            case UpRight, Right, DownRight -> col + 1;
        };
    }
}
