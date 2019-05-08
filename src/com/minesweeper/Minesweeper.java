package com.minesweeper;

import javax.swing.*;

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

//        SwingUtilities.invokeLater(() -> {
//            Interface uInterface = new Interface();
//            uInterface.setView(uInterface.menu());
//        });
    }

}
