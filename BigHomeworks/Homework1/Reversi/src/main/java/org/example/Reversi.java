package org.example;

import java.util.Scanner;

/**
 * Класс игры в реверси.
 */
public final class Reversi implements Executable {
    /**
     * Меню.
     */
    private final Menu MENU = new Menu();
    /**
     * Таблица лучших результатов игроков.
     */
    private final LeaderBoard LEADER_BOARD = new LeaderBoard();
    /**
     * Игрок.
     */
    private final Gamer PLAYER = new Player("Игрок", Color.BLACK);
    /**
     * Компьютер.
     */
    private final Gamer COMPUTER = new Computer("Компьютер", Color.WHITE);
    /**
     * Игрок 1.
     */
    private final Gamer PLAYER1 = new Player("Игрок 1", Color.BLACK);
    /**
     * Игрок 2.
     */
    private final Gamer PLAYER2 = new Player("Игрок 2", Color.WHITE);

    /**
     * Команда запуска режима "Игрок против компьютера".
     */
    private final String PVC_COMMAND = "pvc";
    /**
     * Команда запуска режима "Игрок против игрока".
     */
    private final String PVP_COMMAND = "pvp";
    /**
     * Команда вывода правил.
     */
    private final String HELP_COMMAND = "help";
    /**
     * Команда выхода из игры.
     */
    private final String EXIT_COMMAND = "exit";

    /**
     * Конструктор игры.
     */
    public Reversi() {
        setupMenu();
        setupLeaderBoard();
    }

    /**
     * Запускает игру.
     */
    public void start() {
        showMainMenu();
        System.out.println();
        System.out.print("Введите команду (указаны в скобках): ");
        var in = new Scanner(System.in);
        String command = in.nextLine();
        while (!EXIT_COMMAND.equals(command)) {
            if (PVC_COMMAND.equals(command)) {
                newGame(PVC_COMMAND);
            } else if (PVP_COMMAND.equals(command)) {
                newGame(PVP_COMMAND);
            } else if (HELP_COMMAND.equals(command)) {
                showHelp();
            } else {
                System.out.println("Неизвестная команда!");
                System.out.print("Введите команду (указаны в скобках): ");
                command = in.nextLine();
                continue;
            }
            updateLeaderBoard();
            showMainMenu();
            System.out.println();
            System.out.print("Введите команду (указаны в скобках): ");
            command = in.nextLine();
        }
        finish();
    }

    /**
     * Выходит из игры.
     */
    private void finish() {
        System.out.println("До скорой встречи!");
    }

    /**
     * Выводит главное меню.
     */
    private void showMainMenu() {
        MENU.show();
        System.out.println();
        LEADER_BOARD.show();
    }

    /**
     * Выводит правила.
     */
    private void showHelp() {
        System.out.println("В игре используется квадратная доска размером 8 × 8 клеток (все клетки могут быть одного цвета) и 64 специальные фишки, окрашенные с разных сторон в контрастные цвета, например, в белый и чёрный. Клетки доски нумеруются от верхнего левого угла: вертикали — латинскими буквами, горизонтали — цифрами (по сути дела, можно использовать шахматную доску). Один из игроков играет белыми, другой — чёрными. Делая ход, игрок ставит фишку на клетку доски «своим» цветом вверх.\n" +
                "\n" +
                "В начале игры в центр доски выставляются 4 фишки: чёрные на d5 и e4, белые на d4 и e5.\n" +
                "\n" +
                "\t● Первый ход делают чёрные. Далее игроки ходят по очереди.\n" +
                "\t● Делая ход, игрок должен поставить свою фишку на одну из клеток доски таким образом, чтобы между этой поставленной фишкой и одной из имеющихся уже на доске фишек его цвета находился непрерывный ряд фишек соперника, горизонтальный, вертикальный или диагональный (другими словами, чтобы непрерывный ряд фишек соперника оказался «закрыт» фишками игрока с двух сторон). Все фишки соперника, входящие в «закрытый» на этом ходу ряд, переворачиваются на другую сторону (меняют цвет) и переходят к ходившему игроку.\n" +
                "\t● Если в результате одного хода «закрывается» одновременно более одного ряда фишек противника, то переворачиваются все фишки, оказавшиеся на тех «закрытых» рядах, которые идут от поставленной фишки.\n" +
                "\t● Игрок вправе выбирать любой из возможных для него ходов. Если игрок имеет возможные ходы, он не может отказаться от хода. Если игрок не имеет допустимых ходов, то ход передаётся сопернику.\n" +
                "\t● Игра прекращается, когда на доску выставлены все фишки или когда ни один из игроков не может сделать хода. По окончании игры проводится подсчёт фишек каждого цвета, и игрок, чьих фишек на доске выставлено больше, объявляется победителем. В случае равенства количества фишек засчитывается ничья.");
        System.out.println();
    }

    /**
     * Подготавливает меню.
     */
    private void setupMenu() {
        MENU.setLength(30);
        MENU.setTitle("Reversi");
        MENU.addMenuItem("Игрок против компьютера", "(" + PVC_COMMAND + ")");
        MENU.addMenuItem("Игрок против игрока", "(" + PVP_COMMAND + ")");
        MENU.addMenuItem("Правила", "(" + HELP_COMMAND + ")");
        MENU.addMenuItem("Выйти", "(" + EXIT_COMMAND + ")");
    }

    /**
     * Подготавливает таблицу лучших результатов игроков.
     */
    private void setupLeaderBoard() {
        LEADER_BOARD.setTitle("Лучшие результаты игроков:");
        LEADER_BOARD.setNameLength(20);
        LEADER_BOARD.setScoreLength(6);
        LEADER_BOARD.addLeaderBoardItem(PLAYER.NAME, PLAYER.getHighScore());
        LEADER_BOARD.addLeaderBoardItem(COMPUTER.NAME, COMPUTER.getHighScore());
        LEADER_BOARD.addLeaderBoardItem(PLAYER1.NAME, PLAYER1.getHighScore());
        LEADER_BOARD.addLeaderBoardItem(PLAYER2.NAME, PLAYER2.getHighScore());
    }

    /**
     * Обновляет таблицу лучших результатов игроков.
     */
    private void updateLeaderBoard() {
        LEADER_BOARD.getLeaderBoardItem(0).setScore(PLAYER.getHighScore());
        LEADER_BOARD.getLeaderBoardItem(1).setScore(COMPUTER.getHighScore());
        LEADER_BOARD.getLeaderBoardItem(2).setScore(PLAYER1.getHighScore());
        LEADER_BOARD.getLeaderBoardItem(3).setScore(PLAYER2.getHighScore());
    }

    /**
     * Запускает новую партию игры.
     * @param id идентификатор игры.
     */
    private void newGame(String id) {
        ReversiRound round;
        if (PVC_COMMAND.equals(id)) {
            round = new ReversiRound(PLAYER, COMPUTER);
            round.start();
        } else if (PVP_COMMAND.equals(id)) {
            round = new ReversiRound(PLAYER1, PLAYER2);
            round.start();
        }
    }
}
