package com.minesweeper;

/**
 * Minesweeper ver.2.0
 * GUI version
 *
 * created by Fukkatsumi
 */

public class Minesweeper {
    public static void main(String... args){
        Runnable runnable = () -> {
            Controller game = new Controller();
            game.start();
        };

        runnable.run();
    }

}
