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
        if(board.get(p).isClicked()){
            View.clicked();
        }
    }
}

class Field{
    private char type;
    private final char defChar = '*';
    private boolean isVisible;
    private boolean isClicked;

    public char getType() {
        return type;
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
