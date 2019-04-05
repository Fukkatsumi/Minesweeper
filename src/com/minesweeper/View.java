package com.minesweeper;

public class View {
    public void showMenu(){
        System.out.println("============== Minesweeper ver.1.0 ==============\n" +
                "==============> To start game press 's'\n" +
                "==============> To change settings press 'c'\n" +
                "==============> To exit press 'e'");
    }

    public void showSettings(){
        System.out.println("============== Settings ==============\n" +
                "==============> To change field size press 'f'\n" +
                "==============> To change bombs count 'b'\n" +
                "==============> To quit press 'q'");
    }

    public void showGame(){
        System.out.println("======>Quit - 'q'\n" +
                "======>Restart - 'r'\n" +
                "======>New game - 'n'");
        showBoard();
    }

    public void showBoard(){
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

    public void showClicked(){
        System.out.println("****** You already opened this field******");
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
