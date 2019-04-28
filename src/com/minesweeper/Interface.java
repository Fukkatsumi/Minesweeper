package com.minesweeper;

import javax.swing.*;
import java.awt.*;

public class Interface {
    private JFrame GUIframe;

    private JButton button(String title){
        JButton button = new JButton(title);
        button.setActionCommand(title);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(Color.CYAN.darker());
        return button;
    }

    public JPanel menu(){
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
        menuPanel.add(Box.createVerticalGlue());
        menuPanel.add(button("New Game"));
        menuPanel.add(Box.createVerticalGlue());
        menuPanel.add(button("Settings"));
        menuPanel.add(Box.createVerticalGlue());
        menuPanel.add(button("Exit"));
        menuPanel.add(Box.createVerticalGlue());
        menuPanel.setBackground(Color.darkGray);
        return menuPanel;
    }

    public void settings(){

    }

    public JPanel game(){
        JPanel gamePanel = new JPanel();
        return gamePanel;
    }

    private JPanel board(){
        JPanel boardPanel = new JPanel();
        return boardPanel;
    }

    public void setView(JPanel panel){
        GUIframe.setContentPane(panel);
        GUIframe.setVisible(true);
    }

    public Interface() {
        GUIframe = new JFrame("Minesweeper ver.2.0");
        GUIframe.setSize(new Dimension(220,180));
        GUIframe.setLocationRelativeTo(null);
        GUIframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

