package com.minesweeper;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Model {
    private Map<Point, Field> board;
    private boolean gameOver;
    private boolean winner;
    private int flagged;

    public Map<Point, Field> getBoard() {
        return board;
    }

    public void newGame(){
        Constants.openedBomb = 0;
        Constants.turnNumber = 0;
        createBoard();
        fillBoard();
        gameOver = false;
    }

    public void createBoard() {
        this.board = new HashMap<>();
    }

    public void setBombs(){
        int count = 0;
        do{
            Random r = new Random();
            int x = r.nextInt(Constants.boardSize);
            int y = r.nextInt(Constants.boardSize);
            if (!board.containsKey(new Point(x,y))) {
                    board.put(new Point(x, y), new Field(Constants.BOMB));
                    count++;
            }
        }while (count != Constants.bombCount);
    }

    /* Finding bombs nearby
     * (x-1,y-1) (x,y-1) (x+1,y-1)
     *  (x-1,y)   (x,y)   (x+1,y)
     * (x-1,y+1) (x,y+1) (x+1,y+1)
     * */
    public Field bombsNearby(Point p){
        int bombCount = 0;
        for (int i = p.x - 1; i < p.x + 2; i++) {
            if (i < 0 || i > Constants.boardSize) continue;
            for (int j = p.y - 1; j < p.y + 2; j++) {
                if (j < 0 || j > Constants.boardSize) continue;
                if (i == p.x && j == p.y) continue;
                if(board.containsKey(new Point(i,j))) {
                    if (board.get(new Point(i,j)).getType() == Constants.BOMB) {
                        bombCount++;
                    }
                }
            }
        }
        return bombCount > 0? new Field(String.valueOf(bombCount).charAt(0)) : new Field(Constants.EMPTY);
    }

    public void fillBoard(){
        setBombs();
        Point p;
        for (int i = 0; i < Constants.boardSize; i++){
            for (int j = 0; j < Constants.boardSize; j++){
                p = new Point(i,j);
                if(!board.containsKey(p)){
                    board.put(p,bombsNearby(p));
                }
            }
        }
    }

    public boolean visible(Point p){
        return board.get(p).isVisible();
    }

    public void open(Point p){
            Field field = board.get(p);
                if (field.getType() == Constants.BOMB){
                    showAllBombs();
                }else {
                    field.setVisible(true);
                }

    }

    public void flag(Point p){
        Field field = board.get(p);
        if(!field.isVisible()){
            if(field.getState() == Constants.HIDDEN) {
                if(field.getType() == Constants.BOMB) {
                    Constants.openedBomb++;
                }
                flagged++;
                field.setState(Constants.CHECKED);
            }else {
                if(field.getType() == Constants.BOMB) {
                    Constants.openedBomb--;
                }
                flagged--;
                field.setState(Constants.HIDDEN);
            }
        }
        if(flagged == Constants.bombCount) {
            if (Constants.openedBomb == Constants.bombCount) {
                winner = true;
            }
        }
    }

    public void explore(Point p){
        Point current = p;
        if (board.containsKey(current)){
            if (!board.get(current).isVisible()) {
                open(current);
                if (board.get(current).getType() == Constants.EMPTY) {
                    correct(current);
                }
            }
        }
    }

    /* Check nearby fields on empties
     * (x-1,y-1) (x,y-1) (x+1,y-1)
     *  (x-1,y)   (x,y)   (x+1,y)
     * (x-1,y+1) (x,y+1) (x+1,y+1)
     * */
    public void correct(Point p){
        for (int i = p.x - 1; i < p.x + 2; i++) {
            if (i < 0 || i > Constants.boardSize) continue;
            for (int j = p.y - 1; j < p.y + 2; j++) {
                if (j < 0 || j > Constants.boardSize) continue;
                if (i == p.x && j == p.y) continue;
                if(board.containsKey(new Point(i,j))) {
                    if (board.get(new Point(i,j)).getType() != Constants.EMPTY) {
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
            if(field.getType() == Constants.BOMB){
                field.setVisible(true);
            }
        }
        gameOver = !gameOver;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public boolean isWinner(){
        return winner;
    }


}


