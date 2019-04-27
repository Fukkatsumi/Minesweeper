package com.minesweeper;

import javax.swing.*;
import java.awt.*;

public class Interface {
    private JFrame GUIframe;

    private JButton button(String title, Action action){
        JButton button = new JButton(title);
        button.setAction(action);
        return button;
    }

    private JPanel board(){
        JPanel boardPanel = new JPanel();
        return boardPanel;
    }

    public JPanel menu(){
        JPanel menuPanel = new JPanel();
        return menuPanel;
    }

    public JPanel game(){
        JPanel gamePanel = new JPanel();
        return gamePanel;
    }

    public void setView(JPanel panel){
        GUIframe.setContentPane(panel);
        GUIframe.setVisible(true);
    }

    public Interface() {
        GUIframe = new JFrame("Minesweeper ver.2.0");
        GUIframe.setSize(new Dimension(200,300));
        GUIframe.setLocationRelativeTo(null);
        GUIframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

