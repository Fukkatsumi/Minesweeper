package com.minesweeper;

public class Settings {
    public static int fieldSize = 5;
    public static int bombCount = 10;

    public static void setFieldSize(int fieldSize) {
        Settings.fieldSize = fieldSize;
    }

    public static void setBombCount(int bombCount) {
        Settings.bombCount = bombCount;
    }
}
