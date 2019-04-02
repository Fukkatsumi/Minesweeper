package com.minesweeper;

public class View {
    public static void showMenu(){
        System.out.println("============== Minesweeper ver.1.0 ==============\n" +
                "==============> To start game press 's'\n" +
                "==============> To change settings press 'c'\n" +
                "==============> To exit press 'e'");
    }

    public static void showSettings(){
        System.out.println("============== Constants ==============\n" +
                "==============> To change field size press 'f'\n" +
                "==============> To change bombs count 'b'\n" +
                "==============> To quit press 'q'");
    }

    public static void showGame(){
        System.out.println("======>Quit - 'q'\n" +
                "======>Restart - 'r'\n" +
                "======>New game - 'n'");
        showBoard();
        System.out.println("Turn " + Constants.turnNumber + ":\n" +
                "Set cords:");
    }

    public static void showBoard(){
        char ch = '*';
        System.out.print("  ");
        for(int i = 0; i < Constants.fieldSize; i++){
            System.out.print((i+1) + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < Constants.fieldSize; i++){
            System.out.print((i+1) + " ");
            for (int j = 0; j < Constants.fieldSize; j++){
                System.out.print(ch + " ");
            }
            System.out.print("\n");
        }
    }
}
