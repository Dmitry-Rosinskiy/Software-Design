package org.example;

import java.util.ArrayList;

/**
 * Класс партии игры в реверси.
 */
public final class ReversiRound implements Executable {
    /**
     * Игрок 1.
     */
    private final Gamer PLAYER1;
    /**
     * Игрок 2.
     */
    private final Gamer PLAYER2;
    /**
     * Текущий игрок.
     */
    private Gamer currentPlayer;
    /**
     * Игровая доска.
     */
    private final Board BOARD = new Board(8);

    /**
     * Конструктор партии по двум игрокам.
     * @param player1 первый игрок
     * @param player2 второй игрок
     */
    ReversiRound(Gamer player1, Gamer player2) {
        PLAYER1 = player1;
        PLAYER2 = player2;
        prepareBoard();
        if (player1.IS_COMPUTER) {
            ((Computer) player1).addBoard(BOARD);
        }
        if (player2.IS_COMPUTER) {
            ((Computer) player2).addBoard(BOARD);
        }
        currentPlayer = player1;
    }

    /**
     * Запускает партию.
     */
    public void start() {
        System.out.println("\t\tПартия началась!");
        int passCount = 0;
        while (!BOARD.isFull() && getDiskCount(Color.BLACK) > 0 && getDiskCount(Color.WHITE) > 0 && passCount < 2) {
            ArrayList<String> moves = getPossibleMoves();
            addPossibleMoves(moves);
            BOARD.show();
            removePossibleMoves(moves);
            showScore();
            System.out.println();
            System.out.println("Ходит:\t" + currentPlayer.NAME
                    + (currentPlayer.COLOR == Color.BLACK ? " (чёрные)" : " (белые)"));
            showMoves(moves);
            System.out.println();
            if (!moves.isEmpty()) {
                passCount = 0;
                String move = currentPlayer.makeMove(moves);
                makeMove(move);
            } else {
                System.out.println(currentPlayer.NAME + " пропускает ход");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {
                }
                ++passCount;
            }
            changeCurrentPlayer();
        }
        finish();
    }

    /**
     * Заканчивает партию.
     */
    private void finish() {
        BOARD.show();
        showScore();
        System.out.println();
        int scorePlayer1 = getDiskCount(PLAYER1.COLOR);
        int scorePlayer2 = getDiskCount(PLAYER2.COLOR);
        if (scorePlayer1 > scorePlayer2) {
            System.out.println(PLAYER1.NAME + " победил!\t" + "(очков: " + scorePlayer1 + ")");
            if (scorePlayer1 > PLAYER1.getHighScore()) {
                PLAYER1.setHighScore(scorePlayer1);
            }
        } else if (scorePlayer2 > scorePlayer1) {
            System.out.println(PLAYER2.NAME + " победил!\t" + "(очков: " + scorePlayer2 + ")");
            if (scorePlayer2 > PLAYER2.getHighScore()) {
                PLAYER2.setHighScore(scorePlayer2);
            }
        } else {
            System.out.println("Ничья!");
        }
        System.out.println();
    }

    /**
     * Подготавливает игровую доску.
     */
    private void prepareBoard() {
        BOARD.setObject(new Disk(Color.WHITE), BOARD.SIZE / 2 - 1, BOARD.SIZE / 2 - 1);
        BOARD.setObject(new Disk(Color.BLACK), BOARD.SIZE / 2 - 1, BOARD.SIZE / 2);
        BOARD.setObject(new Disk(Color.BLACK), BOARD.SIZE / 2, BOARD.SIZE / 2 - 1);
        BOARD.setObject(new Disk(Color.WHITE), BOARD.SIZE / 2, BOARD.SIZE / 2);
    }

    /**
     * Выводит очки игроков.
     */
    private void showScore() {
        System.out.println("Чёрные:\t" + getDiskCount(Color.BLACK));
        System.out.println("Белые:\t" + getDiskCount(Color.WHITE));
    }

    /**
     * Выводит возможные ходы.
     * @param moves список возможных ходов
     */
    private void showMoves(ArrayList<String> moves) {
        System.out.print("Возможные ходы:");
        int count = 0;
        for (String move : moves) {
            if (count == 0) {
                System.out.println();
            }
            System.out.print(move + "\t");
            count = (count + 1) % 4;
        }
        System.out.println();
    }

    /**
     * Добавляет возможные ходы на игровой доске.
     * @param moves список возможных ходов
     */
    private void addPossibleMoves(ArrayList<String> moves) {
        for (String move : moves) {
            BOARD.setObject(new PossibleMove(), Converter.convertToRow(move), Converter.convertToCol(move));
        }
    }

    /**
     * Удаляет возможные ходы на игровой доске.
     * @param moves список возможных ходов
     */
    private void removePossibleMoves(ArrayList<String> moves) {
        for (String move : moves) {
            BOARD.removeObject(Converter.convertToRow(move), Converter.convertToCol(move));
        }
    }

    /**
     * Возвращает количество фишек на игровой доске заданного цвета.
     * @param color цвет
     * @return количество фишек
     */
    private int getDiskCount(Color color) {
        int count = 0;
        for (int i = 0; i < BOARD.SIZE; ++i) {
            for (int j = 0; j < BOARD.SIZE; ++j) {
                Object object = BOARD.getObject(i, j);
                if (object != null && ((Disk) object).getColor() == color) {
                    ++count;
                }
            }
        }
        return count;
    }

    /**
     * Меняет текущего игрока.
     */
    private void changeCurrentPlayer() {
        if (currentPlayer == PLAYER1) {
            currentPlayer = PLAYER2;
        } else {
            currentPlayer = PLAYER1;
        }
    }

    /**
     * Возвращает список возможных ходов.
     * @return список возможных ходов
     */
    private ArrayList<String> getPossibleMoves() {
        Color opponentColor = currentPlayer.COLOR == Color.WHITE ? Color.BLACK : Color.WHITE;
        ArrayList<String> moves = new ArrayList<>();
        for (int i = 0; i < BOARD.SIZE; ++i) {
            for (int j = 0; j < BOARD.SIZE; ++j) {
                if (BOARD.getObject(i, j) == null && hasNearDisk(i, j, opponentColor) && checkOccupiedLine(i, j, opponentColor)) {
                    moves.add(Converter.convertToPosition(i, j));
                }
            }
        }
        return moves;
    }

    /**
     * Делает ход и обновляет данные на игровой доске.
     * @param move ход
     */
    private void makeMove(String move) {
        int row = Converter.convertToRow(move);
        int col = Converter.convertToCol(move);
        BOARD.setObject(new Disk(currentPlayer.COLOR), row, col);
        Color opponentColor = currentPlayer.COLOR == Color.WHITE ? Color.BLACK : Color.WHITE;
        for (Direction direction : Direction.values()) {
            if (checkOccupiedLineDirection(row, col, direction, opponentColor)) {
                changeOpponentDiskColorDirection(row, col, direction, opponentColor);
            }
        }
    }

    /**
     * Проверяет, есть ли замкнутые фишки противника между возможной фишкой и другой фишкой игрока.
     * @param row индекс строки возможной фишки
     * @param col индекс столбца возможной фишки
     * @param opponentColor игровой цвет противника
     * @return true, если есть, и false в противном случае
     */
    private boolean checkOccupiedLine(int row, int col, Color opponentColor) {
        for (Direction direction : Direction.values()) {
            if (checkOccupiedLineDirection(row, col, direction, opponentColor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверяет, есть ли замкнутые фишки противника между возможной фишкой и другой фишкой игрока по направлению.
     * @param row индекс строки возможной фишки
     * @param col индекс столбца возможной фишки
     * @param direction направление
     * @param opponentColor игровой цвет противника
     * @return true, если есть, и false в противном случае
     */
    private boolean checkOccupiedLineDirection(int row, int col, Direction direction, Color opponentColor) {
        boolean foundOpponentDisk = false;
        row = advanceRowDirection(row, direction);
        col = advanceColDirection(col, direction);
        while (BOARD.isValidCell(row, col) && BOARD.getObject(row, col) != null) {
            if (((Disk) BOARD.getObject(row, col)).getColor() == opponentColor) {
                foundOpponentDisk = true;
            } else return foundOpponentDisk;
            row = advanceRowDirection(row, direction);
            col = advanceColDirection(col, direction);
        }
        return false;
    }

    /**
     * Меняет цвет фишек противника между фишками игрока по направлению.
     * @param row индекс строки одной фишки
     * @param col индекс столбца одной фишки
     * @param direction направление
     * @param opponentColor игровой цвет противника
     */
    private void changeOpponentDiskColorDirection(int row, int col, Direction direction, Color opponentColor) {
        row = advanceRowDirection(row, direction);
        col = advanceColDirection(col, direction);
        while (BOARD.isValidCell(row, col)) {
            var disk = ((Disk) BOARD.getObject(row, col));
            if (disk.getColor() == opponentColor) {
                disk.changeColor();
            } else {
                break;
            }
            row = advanceRowDirection(row, direction);
            col = advanceColDirection(col, direction);
        }
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

    /**
     * Проверяет наличие фишек заданного цвеета вокруг заданной поизции.
     * @param row индекс строки
     * @param col индекс столбца
     * @param color цвет
     * @return true, если есть хотя бы одна, и false в противном случае.
     */
    private boolean hasNearDisk(int row, int col, Color color) {
        if (row - 1 >= 0 && col - 1 >= 0) {
            Object disk = BOARD.getObject(row - 1, col - 1);
            if (disk != null && ((Disk) disk).getColor() == color) {
                return true;
            }
        }
        if (BOARD.isValidCell(row - 1, col)) {
            Object disk = BOARD.getObject(row - 1, col);
            if (disk != null && ((Disk) disk).getColor() == color) {
                return true;
            }
        }
        if (BOARD.isValidCell(row - 1, col + 1)) {
            Object disk = BOARD.getObject(row - 1, col + 1);
            if (disk != null && ((Disk) disk).getColor() == color) {
                return true;
            }
        }
        if (BOARD.isValidCell(row, col - 1)) {
            Object disk = BOARD.getObject(row, col - 1);
            if (disk != null && ((Disk) disk).getColor() == color) {
                return true;
            }
        }
        if (BOARD.isValidCell(row, col + 1)) {
            Object disk = BOARD.getObject(row, col + 1);
            if (disk != null && ((Disk) disk).getColor() == color) {
                return true;
            }
        }
        if (BOARD.isValidCell(row + 1, col - 1)) {
            Object disk = BOARD.getObject(row + 1, col - 1);
            if (disk != null && ((Disk) disk).getColor() == color) {
                return true;
            }
        }
        if (BOARD.isValidCell(row + 1, col)) {
            Object disk = BOARD.getObject(row + 1, col);
            if (disk != null && ((Disk) disk).getColor() == color) {
                return true;
            }
        }
        if (BOARD.isValidCell(row + 1, col + 1)) {
            Object disk = BOARD.getObject(row + 1, col + 1);
            if (disk != null && ((Disk) disk).getColor() == color) {
                return true;
            }
        }

        return false;
    }
}
