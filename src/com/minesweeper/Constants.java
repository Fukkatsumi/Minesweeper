package com.minesweeper;

public class Constants {

    /* TYPES */
    public static final char BOMB = 'b';
    public static final char EMPTY = '_';

    /* STATES */
    public static final char HIDDEN = '*';
    public static final char CHECKED = '^';

    /* OTHER */
    public static int fieldSize = 5;
    public static int bombCount = 10;
    public static int turnNumber = 0;
    public static int score = 0;

    public static void setFieldSize(int fieldSize) {
        Constants.fieldSize = fieldSize;
    }

    public static void setBombCount(int bombCount) {
        Constants.bombCount = bombCount;
    }
}
