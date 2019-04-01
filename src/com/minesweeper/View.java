package com.minesweeper;

public class View {
    public static void showMenu(){
        System.out.println("============== Welcome to Minesweeper ver.1.0. ==============\n" +
                "==============> To start game press 's'\n" +
                "==============> To change settings press 'c'\n" +
                "==============> To exit press 'e'");
    }

    public static void showSettings(){
        System.out.println("============== Settings ==============\n" +
                "==============> To change field size press 'f'\n" +
                "==============> To change bombs count 'b'\n" +
                "==============> To quit press 'q'");
    }
}
