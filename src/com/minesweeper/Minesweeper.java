package com.minesweeper;

import com.minesweeper.Interface.Console.Controller;
import com.minesweeper.Interface.GUI.SwingInterface;

import javax.swing.*;

/**
 * Minesweeper ver.3.0
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
