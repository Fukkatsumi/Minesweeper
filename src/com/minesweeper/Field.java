package com.minesweeper;

public class Field {
    private char type;
    private char state = Constants.HIDDEN;
    private boolean isVisible = false;

    public char getType() {
        return type;
    }

    public char getState() {
        return state;
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

    public Field(char type) {
        this.type = type;
    }
}
