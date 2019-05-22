package com.minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "New Game":
                Minesweeper.game.model.newGame();
                Minesweeper.uSwingInterface.setBoard(Minesweeper.game.model.getBoard());
                Minesweeper.uSwingInterface.setView(Minesweeper.uSwingInterface.pGame());
                break;
            case "Settings":
                Minesweeper.uSwingInterface.setView(Minesweeper.uSwingInterface.pSettings());
                break;
            case "OK":
                break;
            case "Cancel":
            case "Quit to pMenu":
                Minesweeper.uSwingInterface.setView(Minesweeper.uSwingInterface.pMenu());
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }
}
