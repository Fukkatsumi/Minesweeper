package com.minesweeper;

import java.awt.*;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class Model {
    private static Logger log = Logger.getLogger(Model.class.getName());
    private Map<Point, Field> board;
    private boolean gameOver;

    public void setBombs(){
        for (int i = 0; i < Constants.bombCount; i++){
            Random r = new Random();
            int x = r.nextInt(Constants.fieldSize);
            int y = r.nextInt(Constants.fieldSize);
            if(!board.containsKey(new Point(x,y))){
                board.put(new Point(x,y), new Field(Constants.BOMB));
            }
        }
    }

    public Field bombsNearby(Point p){
        int bombCount = 0;
        for (int i = p.x - 1; i < p.x + 2; i++) {
            for (int j = p.y - 1; j < p.y + 2; j++) {
                if (p.x < 0 || p.x > Constants.fieldSize) continue;
                if (p.y < 0 || p.y > Constants.fieldSize) continue;
                if (board.get(p).getType() == Constants.BOMB){
                    bombCount++;
                }
            }
        }
        return bombCount > 0? new Field((char) bombCount) : new Field(Constants.EMPTY);
    }

    /* Finding bombs nearby
    * (x-1,y-1) (x,y-1) (x+1,y-1)
    *  (x-1,y)   (x,y)   (x+1,y)
    * (x-1,y+1) (x,y+1) (x+1,y+1)
    * */

    public boolean clicked(Point p){
        return board.get(p).isClicked();
    }

    public void open(Point p){
        Field field = board.get(p);
        if(field.getType() == Constants.BOMB){
            showAllBombs();
        }else {

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


