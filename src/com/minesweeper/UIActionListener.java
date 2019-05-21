package com.minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UIActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "New Game":
                Minesweeper.game.model.newGame();
                Minesweeper.uInterface.setBoard(Minesweeper.game.model.getBoard());
                Minesweeper.uInterface.setView(Minesweeper.uInterface.game());
                break;
            case "Settings":
                Minesweeper.uInterface.setView(Minesweeper.uInterface.settings());
                break;
            case "OK":
                break;
            case "Cancel":
            case "Quit to menu":
                Minesweeper.uInterface.setView(Minesweeper.uInterface.menu());
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }
}
