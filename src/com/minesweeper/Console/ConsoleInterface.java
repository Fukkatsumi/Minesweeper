package com.minesweeper.Console;

import com.minesweeper.Field;
import com.minesweeper.View;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ConsoleInterface implements View {
    void showExit() {
        System.out.println("Do you want to exit the game?\n" +
                "   y - Yes     n - No");
    }

    void showError() {
        System.out.println("Wrong input parameter!");
    }

    void showGameOver() {
        System.out.println("============== Game over! ==============\n" +
                "==Input any symbol to return to menu==");
    }

    void showVictory() {
        System.out.println("============== You Win! ==============\n" +
                "==Input any symbol to return to menu==");
    }

    void showGameActions() {
        System.out.println("    To open field press 'o'\n" +
                "    To set flag press 'f'\n");
    }

    void showGetX() {
        System.out.println("input field cords: \n" +
                "x = ");
    }

    void showGetY() {
        System.out.println("y = ");
    }

    public void showCantOpenField() {
        System.out.println("****** You can't open this field ******");
    }

    public void showCantMarkField() {
        System.out.println("****** You can't flag this field ******");
    }

    void showBombCountInput() {
        System.out.println("Input the bomb count:");
    }

    private int bombsCount;

    void getBombsCountToShow(int bombsCount) {
        this.bombsCount = bombsCount;
    }

    void showActualBombsCount() {
        System.out.println("Bombs count = " + this.bombsCount);
    }

    void showBoardSizeInput() {
        System.out.println("Input the board size(4-15):");
    }

    private int boardSize;

    void getBoardSizeToShow(int boardSize) {
        this.boardSize = boardSize;
    }

    void showActualBoardSize() {
        System.out.println("Board size = " + this.boardSize + "x" + this.boardSize);
    }

    private Map<Point, Field> board = new HashMap<>();

    void setBoard(Map<Point, Field> map) {
        this.board.putAll(map);
    }

    @Override
    public void gameInterface() {
        if (board.isEmpty()) {
            return;
        }
        System.out.print("  ");
        for (int i = 0; i < this.boardSize; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < this.boardSize; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < this.boardSize; j++) {
                Point p = new Point(i, j);
                if (!this.board.get(p).isOpen()) {
                    System.out.print(this.board.get(p).getState().getChar() + " ");
                } else {
                    System.out.print(this.board.get(p).getTypeValue() + " ");
                }
            }
            System.out.print("\n");
        }
    }

    @Override
    public void menuInterface() {
        System.out.println("============== Minesweeper ver.1.0 ==============\n" +
                "==============> To start new game press 'n'\n" +
                "==============> To change settings press 's'\n" +
                "==============> To exit press 'e'");
    }

    @Override
    public void settingsInterface() {
        System.out.println("============== Settings ==============\n" +
                "==============> To change board size press 's'\n" +
                "==============> To change bombs count 'c'\n" +
                "==============> To return to menu 'm'");
    }
}
