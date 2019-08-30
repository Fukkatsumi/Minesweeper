package com.minesweeper;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {
    private Map<Point, Field> board;

    private final int DEFAULT_BOARD_SIZE = 9;
    private int boardSize = DEFAULT_BOARD_SIZE;

    public void setBoardSize(int boardSize) {
        if (boardSize < DEFAULT_BOARD_SIZE) {
            this.boardSize = DEFAULT_BOARD_SIZE;
            return;
        }
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Finding bombs around the (x,y) field and calculate them count:
     * (x-1,y-1) (x,y-1) (x+1,y-1)
     * (x-1,y)   (x,y)   (x+1,y)
     * (x-1,y+1) (x,y+1) (x+1,y+1)
     */
    private int bombsNearbyCount(Point p) {
        int bombsCount = 0;
        for (int i = p.x - 1; i < p.x + 2; i++) {
            if (i < 0 || i > boardSize)
                continue;
            for (int j = p.y - 1; j < p.y + 2; j++) {
                if (j < 0 || j > boardSize)
                    continue;
                if (i == p.x && j == p.y)
                    continue;
                if (board.containsKey(new Point(i, j))) {
                    if (board.get(new Point(i, j)).isBomb()) {
                        bombsCount++;
                    }
                }
            }
        }
        return bombsCount;
    }

    void fill() {
        setBombs();
        Point p;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                p = new Point(i, j);
                if (!board.containsKey(p)) {
                    int bombsCount = bombsNearbyCount(p);
                    Field field = bombsCount > 0 ? new Field(Field.Type.NUMBER.setNumber(bombsCount)) : new Field(Field.Type.EMPTY);
                    board.put(p, field);
                }
            }
        }
    }

    /**
     * Finding point to explore
     * (x-1,y-1) (x,y-1) (x+1,y-1)
     * (x-1,y)   (x,y)   (x+1,y)
     * (x-1,y+1) (x,y+1) (x+1,y+1)
     * <p>
     * Used recursively when method explore(Point) finds empty fields
     */
    private void exploreNearby(Point p) {
        Point point = new Point();
        for (int i = p.x - 1; i < p.x + 2; i++) {
            if (i < 0 || i > boardSize)
                continue;
            for (int j = p.y - 1; j < p.y + 2; j++) {
                if (j < 0 || j > boardSize)
                    continue;
                if (i == p.x && j == p.y)
                    continue;
                if (board.containsKey(new Point(i, j))) {
                    point.setLocation(i, j);
                    explore(point);
                }
            }
        }
    }

    private final int DEFAULT_BOMBS_COUNT = 10;
    private int bombsCount = DEFAULT_BOMBS_COUNT;

    public void setBombsCount(int bombsCount) {
        if (bombsCount < DEFAULT_BOMBS_COUNT) {
            this.bombsCount = DEFAULT_BOMBS_COUNT;
            return;
        }
        this.bombsCount = bombsCount;
    }

    public int getBombsCount() {
        return bombsCount;
    }

    private void setBombs() {
        int count = 0;
        do {
            Random r = new Random();
            int x = r.nextInt(boardSize);
            int y = r.nextInt(boardSize);
            if (!board.containsKey(new Point(x, y))) {
                board.put(new Point(x, y), new Field(Field.Type.BOMB));
                count++;
            }
        } while (count != bombsCount);
    }

    public boolean isDefusedAllBombs() {
        return calculateDefusedBombs() == bombsCount;
    }

    private int markedFieldsCount;

    public void defuseBomb(Point p) {
        Field field = board.get(p);
        if (!field.isOpen()) {
            if (!field.isMarked()) {
                if (markedFieldsCount >= bombsCount) {
                    return;
                }
            }
            field.mark();
        }
    }

    private int calculateDefusedBombs() {
        markedFieldsCount = 0;
        int defusedBombs = 0;
        for (Field field : board.values()) {
            if (field.isMarked()) {
                if (field.isBomb()) {
                    defusedBombs++;
                }
                markedFieldsCount++;
            }
        }
        return defusedBombs;
    }

    void explore(Point p) {
        Field currentField = board.get(p);
        if (!currentField.isOpen()) {
            if (!currentField.isBomb()) {
                currentField.open();
                if (currentField.isEmpty()) {
                    exploreNearby(p);
                }
            } else {
                detonateAllBombs();
            }
        }
    }

    private boolean detonatedAllBombs;

    private void detonateAllBombs() {
        for (Field field : board.values()) {
            if (field.isBomb()) {
                field.open();
            }
        }
        detonatedAllBombs = true;
    }

    public boolean isDetonatedAllBombs() {
        return detonatedAllBombs;
    }

    public Map<Point, Field> getBoard() {
        return this.board;
    }

    public Board() {
        this.board = new HashMap<>();
    }
}


