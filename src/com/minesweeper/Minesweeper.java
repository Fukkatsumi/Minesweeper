package com.minesweeper;

import javax.swing.*;

/**
 * Minesweeper ver.2.0
 * GUI version
 *
 * created by Fukkatsumi
 */

public class Minesweeper {
    public static SwingInterface uSwingInterface;
    public static Controller game;
    public static void main(String... args){
        SwingUtilities.invokeLater(() -> {
            game = new Controller();
            uSwingInterface = new SwingInterface();
            uSwingInterface.setView(uSwingInterface.pMenu());
        });
    }

}
