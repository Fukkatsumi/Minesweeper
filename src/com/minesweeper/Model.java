package com.minesweeper;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class Model {
    private static Logger log = Logger.getLogger(Model.class.getName());
    private Map<Point, Field> board;
    private boolean gameOver;

    public Map<Point, Field> getBoard() {
        return board;
    }

    public void newGame(){
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
            int x = r.nextInt(Constants.boardSize + 1);
            int y = r.nextInt(Constants.boardSize + 1);
            if(!board.containsKey(new Point(x,y))){
                board.put(new Point(x,y), new Field(Constants.BOMB));
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
            for (int j = p.y - 1; j < p.y + 2; j++) {
                if (i < 0 || i > Constants.boardSize) continue;
                if (j < 0 || j > Constants.boardSize) continue;
                if (i == p.x && i == p.y) continue;
                if(board.containsKey(new Point(i,j))) {
                    if (board.get(new Point(i, j)).getType() == Constants.BOMB) {
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

    public boolean clicked(Point p){
        return board.get(p).isClicked();
    }

    public void open(Point p){
        Field field = board.get(p);
        if(field.getType() == Constants.BOMB){
            showAllBombs();
        }else {
            field.setClicked();
            field.setVisible(true);
        }
    }

    public void flag(Point p){
        Field field = board.get(p);
        if(!field.isVisible()){
            if(field.getState() == Constants.HIDDEN) {
                field.setState(Constants.CHECKED);
            }else {
                field.setState(Constants.HIDDEN);
            }
        }
    }

    public void showSafeArea(){

    }

    public void showAllBombs(){
        for(Field field: board.values()){
            if(field.getType() == Constants.BOMB){
                field.setVisible(true);
            }
        }
        gameOver = !gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }


}


