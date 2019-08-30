package com.minesweeper;

import java.awt.*;
import java.util.Map;

public class Game {
    static Board gameBoard;

    public void newGame() {
        gameBoard = new Board();
        gameBoard.fill();
    }

    public Map<Point, Field> getBoard() {
        if (gameBoard.getBoard().isEmpty()) {
            return null;
        }
        return gameBoard.getBoard();
    }

    public boolean lose() {
        return gameBoard.isDetonatedAllBombs();
    }

    public boolean win() {
        return gameBoard.isDefusedAllBombs();
    }

    private Point point;

    public void setOperation(boolean isOpenField) {
        if (isOpenField) {
            gameBoard.explore(point);
        } else {
            gameBoard.defuseBomb(point);
        }
    }

    public void setPoint(Point p) {
        point = p;
    }
}
