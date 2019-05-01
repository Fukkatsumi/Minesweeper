package com.minesweeper;

import java.awt.*;
import java.util.Map;

public class View {
    private Interface userInterface = new Interface();

    public void showMenu() {
        userInterface.setView(userInterface.menu());
        System.out.println("============== Minesweeper ver.1.0 ==============\n" +
                "==============> To start game press 'g'\n" +
                "==============> To change settings press 's'\n" +
                "==============> To exit press 'e'");
    }

    public void showSettings() {
        userInterface.setView(userInterface.settings());
        System.out.println("============== Settings ==============\n" +
                "==============> To change board size press 's'\n" +
                "==============> To change bombs count 'c'\n" +
                "==============> To quit press 'q'");
    }

    public void showGame() {
        System.out.println("======>Quit - 'q'\n" +
                "======>Restart - 'r'\n" +
                "======>New game - 'n'");
    }

    public void showBoard(Map<Point, Field> map) {
        System.out.print("  ");
        for(int i = 0; i < Constants.boardSize; i++){
            System.out.print((i+1) + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < Constants.boardSize; i++){
            System.out.print((i+1) + " ");
            for (int j = 0; j < Constants.boardSize; j++){
                Point p = new Point(i,j);
                if(!map.get(p).isVisible()) {
                    System.out.print(map.get(p).getState() + " ");
                }else {
                    System.out.print(map.get(p).getType() + " ");
                }
            }
            System.out.print("\n");
        }
    }

    public void showGameActions() {
        System.out.println("    To open field press 'o'\n" +
                "    To set flag press 'f'\n" +
                "    To cancel press 'c'\n ");
    }

    public void showTurn(){
        System.out.println("Turn " + Constants.turnNumber + ":");
    }

    public void showGetX() {
        System.out.println("Set cords: \n" +
                "x = ");
    }

    public void showGetY() {
        System.out.println("y = ");

    }

    public void showClicked(){
        System.out.println("****** You already opened this field ******");
    }

    public void showFlagged() {
        System.out.println("****** You can't flag this field ******");
    }

    public void showBoardSizeInput(){
        System.out.println("Input the board size:");
    }

    public void showBombCountInput(){
        System.out.println("Input the bomb count:");
    }

    public void showWarning(){
        System.out.println("Wrong parameter!");
    }

    public void showWin() {
        System.out.println("============== You Win! ==============");
        showActions();
    }

    public void showGameOver() {
        System.out.println("============== Game over! ==============");
        showActions();
    }

    private void showActions(){
        System.out.printf("Turns: %d\n" +
                "==============> To exit press 'e'\n" +
                "==============> To restart game press 'r'\n" +
                "==============> To return to main menu press'm'\n", Constants.turnNumber);
    }
}
