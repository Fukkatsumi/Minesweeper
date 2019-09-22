package com.minesweeper;

import com.minesweeper.Swing.SwingGame;

import javax.swing.*;

/**
 * Minesweeper ver.3.1
 * created by Fukkatsumi
 */

public class Minesweeper {
    public static Controller game;

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            game = new SwingGame();
            game.menu();
        });
    }
}
