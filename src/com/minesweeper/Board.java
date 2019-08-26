package com.minesweeper;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {
    private Map<Point, Field> board;
    private int checkedFieldsCount;

    public static int boardSize = 15;
    public static int bombCount = 10;
    public static int openedBomb = 0;

    public static void setBoardSize(int boardSize) {
        Board.boardSize = boardSize;
    }

    public static void setBombCount(int bombCount) {
        Board.bombCount = bombCount;
    }

    public Board() {
        this.board = new HashMap<>();
    }

    void fill(){
        setBombs();
        Point p;
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                p = new Point(i,j);
                if(!board.containsKey(p)){
                    board.put(p,bombsNearby(p));
                }
            }
        }
    }

    private void setBombs(){
        int count = 0;
        do{
            Random r = new Random();
            int x = r.nextInt(boardSize);
            int y = r.nextInt(boardSize);
            if (!board.containsKey(new Point(x,y))) {
                board.put(new Point(x, y), new Field(Field.Type.BOMB));
                count++;
            }
        }while (count != bombCount);
    }

    protected Map<Point, Field> getBoard() {
        return this.board;
    }

    /* Finding bombs nearby
     * (x-1,y-1) (x,y-1) (x+1,y-1)
     *  (x-1,y)   (x,y)   (x+1,y)
     * (x-1,y+1) (x,y+1) (x+1,y+1)
     * */
    public Field bombsNearby(Point p) {
        int bombCount = 0;
        for (int i = p.x - 1; i < p.x + 2; i++) {
            if (i < 0 || i > boardSize) continue;
            for (int j = p.y - 1; j < p.y + 2; j++) {
                if (j < 0 || j > boardSize) continue;
                if (i == p.x && j == p.y) continue;
                if (board.containsKey(new Point(i, j))) {
                    if (board.get(new Point(i, j)).isBomb()) {
                        bombCount++;
                    }
                }
            }
        }
        return bombCount > 0 ? new Field(Field.Type.NUMBER.setNumber(bombCount)) : new Field(Field.Type.EMPTY);
    }


    public boolean visible(Point p){
        return board.get(p).isOpen();
    }

    public void open(Point p){
            Field field = board.get(p);
                if (field.isBomb()){
                    showAllBombs();
                }else {
                    field.open();
                }

    }

//    public void flag(Point p){
//        Field field = board.get(p);
//        if(!field.isVisible()){
//            if(field.getState() == HIDDEN) {
//                if(field.getType() == BOMB) {
//                    openedBomb++;
//                }
//                flagged++;
//                field.setState(CHECKED);
//            }else {
//                if(field.getType() == BOMB) {
//                    openedBomb--;
//                }
//                flagged--;
//                field.setState(HIDDEN);
//            }
//        }
////        if(flagged == bombCount) {
////            if (openedBomb == bombCount) {
////                winner = true;
////            }
////        }
//    }

//    public void explore(Point p){
//        Point current = p;
//        if (board.containsKey(current)){
//            if (!board.get(current).isVisible()) {
//                open(current);
//                if (board.get(current).getType() == EMPTY) {
//                    correct(current);
//                }
//            }
//        }
//    }

    /* Check nearby fields on empties
     * (x-1,y-1) (x,y-1) (x+1,y-1)
     *  (x-1,y)   (x,y)   (x+1,y)
     * (x-1,y+1) (x,y+1) (x+1,y+1)
     * */
    public void explore(Point p){
        for (int i = p.x - 1; i < p.x + 2; i++) {
            if (i < 0 || i > boardSize) continue;
            for (int j = p.y - 1; j < p.y + 2; j++) {
                if (j < 0 || j > boardSize) continue;
                if (i == p.x && j == p.y) continue;

                if(board.containsKey(new Point(i,j))) {
                    if (!board.get(new Point(i,j)).isEmpty()) {
                        open(new Point(i,j));
                    }else {
                        explore(new Point(i,j));
                    }
                }
            }
        }
    }

    public void showAllBombs(){
        for(Field field: board.values()){
            if(field.isBomb()){
                field.open();
            }
        }
        //gameOver = !gameOver;
    }


}


