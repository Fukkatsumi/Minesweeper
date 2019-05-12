package com.minesweeper;

public class Constants {

    /* TYPES */
    public static final char BOMB = 'b';
    public static final char EMPTY = '_';

    /* STATES */
    public static final char HIDDEN = '*';
    public static final char CHECKED = '^';

    /* OTHER */
    public static int boardSize = 15;
    public static int bombCount = 10;
    public static int openedBomb = 0;
    public static int turnNumber = 0;

    public static void setBoardSize(int boardSize) {
        if (boardSize > 4 && boardSize < 41) {
            Constants.boardSize = boardSize;
        } else {
            System.out.println("Wrong value");
        }
    }

    public static void setBombCount(int bombCount) {
        if(bombCount > boardSize - boardSize / 4){
            Constants.bombCount = boardSize / 2;
            System.out.println("Wrong value");
        } else {
            Constants.bombCount = bombCount;
        }
    }
}
