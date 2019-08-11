package com.minesweeper.Interface.GUI;

import com.minesweeper.Action;
import com.minesweeper.Minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MActionListener implements ActionListener {
    private Action action;
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "New Game":
                action.newGame();
                //Minesweeper.uSwingInterface.setBoard(Minesweeper.game.board.getBoard());
                //Minesweeper.uSwingInterface.setView(Minesweeper.uSwingInterface.pGame());
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
