package com.minesweeper;

import com.minesweeper.Swing.SwingGame;

import javax.swing.*;

/**
 * Minesweeper ver.3.0
 * created by Fukkatsumi
 */

public class Minesweeper {
    public static SwingGame game;

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            game = new SwingGame();
            game.menu();
        });
    }
}
