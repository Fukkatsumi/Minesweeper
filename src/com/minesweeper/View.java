package com.minesweeper;

import java.awt.*;
import java.util.Map;

public class View {
    public void showMenu(){
        System.out.println("============== Minesweeper ver.1.0 ==============\n" +
                "==============> To start game press 's'\n" +
                "==============> To change settings press 'c'\n" +
                "==============> To exit press 'e'");
    }

    public void showSettings(){
        System.out.println("============== Settings ==============\n" +
                "==============> To change board size press 's'\n" +
                "==============> To change bombs count 'c'\n" +
                "==============> To quit press 'q'");
    }

    public void showGame(){
        System.out.println("======>Quit - 'q'\n" +
                "======>Restart - 'r'\n" +
                "======>New game - 'n'");
    }

    public void showBoard(Map<Point, Field> map){
        //char ch = '*';
        System.out.print("  ");
        for(int i = 0; i < Constants.boardSize; i++){
            System.out.print((i+1) + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < Constants.boardSize; i++){
            System.out.print((i+1) + " ");
            for (int j = 0; j < Constants.boardSize; j++){
               // System.out.print(ch + " ");

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

    public void showActions(){
        System.out.println("    To open field press 'o'\n" +
                "    To set flag press 'f'\n" +
                "    To cancel press 'c'\n ");
    }

    public void showTurn(){
        System.out.println("Turn " + Constants.turnNumber + ":");
    }

    public void showGetX(){
        System.out.println("Set cords:" +
                "x = ");
    }

    public void showGetY(){
        System.out.println("y = ");

    }

    public void showClicked(){
        System.out.println("****** You already opened this field******");
    }

    public void showWarning(){
        System.out.println("Wrong parameter!");
    }

    public void showGameOver(){
        System.out.printf("============== Game over! ==============\n" +
                "Turns: %d\n" +
                "Your score: %d\n" +
                "==============> To exit press 'e'\n" +
                "==============> To restart game press 'r'\n" +
                "==============> To return to main menu press'm'\n", Constants.turnNumber, Constants.score);
    }
}
