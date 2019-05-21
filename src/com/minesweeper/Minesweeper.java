package com.minesweeper;

import javax.swing.*;

/**
 * Minesweeper ver.2.0
 * GUI version
 *
 * created by Fukkatsumi
 */

public class Minesweeper {
    public static Interface uInterface;
    public static Controller game;
    public static void main(String... args){
        SwingUtilities.invokeLater(() -> {
            game = new Controller();
            uInterface = new Interface();
            uInterface.setView(uInterface.menu());
        });
    }

}
