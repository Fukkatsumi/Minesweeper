package com.minesweeper.Swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingActionListener implements ActionListener {
    private SwingGame swing;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Exit":
                System.exit(0);
                break;
            case "Go to menu":
            case "Back":
            case "Menu":
                swing.menu();
                break;
            case "New Game":
                swing.game();
                break;
            case "OK":
            case "Settings":
                swing.settings();
                break;
            case "Setup":
                swing.setupSettings();
                break;
        }
    }

    public SwingActionListener(SwingGame controller) {
        this.swing = controller;
    }
}
