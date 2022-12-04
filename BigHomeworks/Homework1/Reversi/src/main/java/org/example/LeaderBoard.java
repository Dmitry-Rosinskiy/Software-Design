package org.example;

import java.util.ArrayList;

public class LeaderBoard {
    private int nameLength = 60;
    private int scoreLength = 6;
    private ArrayList<LeaderBoardItem> LEADER_BOARD_ITEM_LIST = new ArrayList<>();
    private String title = "";

    public void setNameLength(int length) {
        nameLength = length;
    }

    public void setScoreLength(int length) {
        this.scoreLength = length;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LeaderBoardItem getLeaderBoardItem(int index) {
        return LEADER_BOARD_ITEM_LIST.get(index);
    }

    public void show() {
        System.out.println(title);
        for (LeaderBoardItem item : LEADER_BOARD_ITEM_LIST) {
            item.show();
        }
    }

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
