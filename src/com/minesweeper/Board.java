package com.minesweeper;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Board {
    private static Map<Point, Field> board;

    private static final int DEFAULT_MIN_BOARD_SIZE = 4;
    private static final int DEFAULT_MAX_BOARD_SIZE = 15;
    private static int boardSize = DEFAULT_MIN_BOARD_SIZE;

    public void setBoardSize(int boardSize) {
        if (boardSize < DEFAULT_MIN_BOARD_SIZE) {
            Board.boardSize = DEFAULT_MIN_BOARD_SIZE;
            return;
        }
        if (boardSize > DEFAULT_MAX_BOARD_SIZE) {
            Board.boardSize = DEFAULT_MAX_BOARD_SIZE;
            return;
        }
        Board.boardSize = boardSize;
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

    public void fill() {
        board = new HashMap<>();
        setBombs();
        Point p;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                p = new Point(i, j);
                if (!board.containsKey(p)) {
                    int bombsCount = bombsNearbyCount(p);
                    Field field = bombsCount > 0 ? new Field(Field.Type.NUMBER, bombsCount) : new Field(Field.Type.EMPTY);
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

    private static final int DEFAULT_MIN_BOMBS_COUNT = 6;
    private static int bombsCount = DEFAULT_MIN_BOMBS_COUNT;

    public void setBombsCount(int bombsCount) {
        if (bombsCount < DEFAULT_MIN_BOMBS_COUNT) {
            Board.bombsCount = DEFAULT_MIN_BOMBS_COUNT;
            return;
        }
        int maxBombsCount = boardSize * boardSize / 2;
        if (bombsCount > maxBombsCount) {
            Board.bombsCount = maxBombsCount;
            return;
        }
        Board.bombsCount = bombsCount;
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

    public void explore(Point p) {
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

    private void detonateAllBombs() {
        for (Field field : board.values()) {
            if (field.isBomb()) {
                field.open();
            }
        }
    }

    public boolean isDetonatedAllBombs() {
        for (Field field : board.values()) {
            if (field.isBomb() && field.isOpen()) {
                return true;
            }
        }
        return false;
    }

    public Map<Point, Field> getBoard() {
        return board;
    }

    public Board() {
        board = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + board +
                ", DEFAULT_MIN_BOARD_SIZE=" + DEFAULT_MIN_BOARD_SIZE +
                ", DEFAULT_MAX_BOARD_SIZE=" + DEFAULT_MAX_BOARD_SIZE +
                ", boardSize=" + boardSize +
                ", DEFAULT_MIN_BOMBS_COUNT=" + DEFAULT_MIN_BOMBS_COUNT +
                ", bombsCount=" + bombsCount +
                ", markedFieldsCount=" + markedFieldsCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board = (Board) o;
        return markedFieldsCount == board.markedFieldsCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(markedFieldsCount);
    }
}


