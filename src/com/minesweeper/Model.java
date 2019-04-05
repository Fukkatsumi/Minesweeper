package com.minesweeper;

import java.awt.*;
import java.util.Map;
import java.util.logging.Logger;

public class Model {
    private static Logger log = Logger.getLogger(Model.class.getName());
    private Map<Point, Field> board;

    public void setBombs(){

    }

    public void bombsNearby(){

    }

    public void click(Point p){
        Field field = board.get(p);
        if(field.isClicked()){
            View.clicked();
        }else {
            if(field.getType() == Constants.BOMB){
                showAllBombs();
                View.gameOver();
            }else {

            }
        }
    }

    public void showAllBombs(){

    }
}

class Field{
    private char type;
    private char state = Constants.HIDDEN;
    private boolean isVisible;
    private boolean isClicked;

    public char getType() {
        return type;
    }

    public void setState(char state) {
        this.state = state;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public Field(char type) {
        this.type = type;
    }
}
