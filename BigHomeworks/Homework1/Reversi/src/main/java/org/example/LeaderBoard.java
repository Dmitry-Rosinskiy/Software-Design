package org.example;

import java.util.ArrayList;

/**
 * Класс таблицы лучших рузультатов.
 */
public final class LeaderBoard {
    /**
     * Длина ячейки таблицы для имени.
     */
    private int nameLength = 60;
    /**
     * Длина ячейки таблицы для счёта.
     */
    private int scoreLength = 6;
    /**
     * Список пунктов таблицы.
     */
    private final ArrayList<LeaderBoardItem> LEADER_BOARD_ITEM_LIST = new ArrayList<>();
    /**
     * Заголовок таблицы.
     */
    private String title = "";

    /**
     * Устанавливает длину ячейки таблицы для имени.
     * @param length длина ячейки
     */
    public void setNameLength(int length) {
        nameLength = length;
    }

    /**
     * Устанавливает длину ячейки таблицы для счёта.
     * @param length длина ячейки
     */
    public void setScoreLength(int length) {
        this.scoreLength = length;
    }

    /**
     * Устанавливает заголовок таблицы.
     * @param title заголовок таблицы
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращение пункта таблицы по индексу
     * @param index индекс пункта
     * @return пункт таблицы
     */
    public LeaderBoardItem getLeaderBoardItem(int index) {
        return LEADER_BOARD_ITEM_LIST.get(index);
    }

    /**
     * Выводит таблицу.
     */
    public void show() {
        System.out.println(title);
        for (LeaderBoardItem item : LEADER_BOARD_ITEM_LIST) {
            item.show();
        }
    }

    /**
     * Добавляет пункт таблицы.
     * @param name имя
     * @param score счёт
     */
    public void addLeaderBoardItem(String name, int score) {
        var leaderBoardItem = new LeaderBoardItem();
        leaderBoardItem.setNameLength(nameLength);
        leaderBoardItem.setScoreLength(scoreLength);
        leaderBoardItem.setIsLast(true);
        leaderBoardItem.setName(name);
        leaderBoardItem.setScore(score);
        LEADER_BOARD_ITEM_LIST.add(leaderBoardItem);
        if (LEADER_BOARD_ITEM_LIST.size() > 1) {
            LEADER_BOARD_ITEM_LIST.get(LEADER_BOARD_ITEM_LIST.size() - 2).setIsLast(false);
        }
    }
}
