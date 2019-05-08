package com.minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("test");
        switch (e.getActionCommand()){
            case "New Game":
                View.userInterface.setView(View.userInterface.game());
                break;
            case "Settings":
                View.userInterface.setView(View.userInterface.settings());
                break;
            case "OK":
                break;
            case "Cancel":
            case "Quit to menu":
                View.userInterface.setView(View.userInterface.menu());
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }
}
