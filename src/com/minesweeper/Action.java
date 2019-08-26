package com.minesweeper;

public class Action {

    public static int turnNumber = 0;

    private boolean gameOver;
    private boolean winner;
    private Board board;

    public void newGame(){
        board = new Board();
        board.fill();
        gameOver = false;
    }

    public void menu(){}

    public void settings(){}

    public void exit(){}

    public void refresh(){}

    public boolean lose(){
        return gameOver;
    }

    public boolean win(){
        return winner;
    }

    public void getPoint(){}

    public void open(){}

    public void flag(){}

    public void cancel(){}
}
