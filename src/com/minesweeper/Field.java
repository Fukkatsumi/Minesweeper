package com.minesweeper;

public class Field {
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
