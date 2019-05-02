package com.minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Interface {
    private JFrame GUIframe;
    private JLabel lBombs;

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

    public JPanel settings(){
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel lBoardSize = new JLabel("Board size: ");
        lBoardSize.setForeground(Color.lightGray);

        settingsPanel.add(lBoardSize, set(c,0,0));

        JLabel lBombCount = new JLabel("Bombs count: ");
        lBombCount.setForeground(Color.lightGray);

        settingsPanel.add(lBombCount,set(c,0,1));

        JTextField boardSizeTF = new JTextField(3);
        boardSizeTF.setText(Integer.toString(Constants.boardSize));
        boardSizeTF.setBackground(Color.gray.brighter());

        settingsPanel.add(boardSizeTF, set(c,1,0));

        JTextField bombCountTF = new JTextField(3);
        bombCountTF.setText(Integer.toString(Constants.bombCount));
        bombCountTF.setBackground(Color.gray.brighter());

        settingsPanel.add(bombCountTF, set(c,1,1));

        settingsPanel.add(button("OK"), set(c,0,2));
        settingsPanel.add(button("Cancel"), set(c,1,2));

        settingsPanel.setBackground(Color.darkGray);
        return settingsPanel;
    }

    public JPanel game(){
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));
        gamePanel.add(tools());
        gamePanel.add(board());
        gamePanel.setBackground(Color.darkGray);
        return gamePanel;
    }

    private JPanel tools(){
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.LINE_AXIS));

        lBombs = new JLabel();
        refreshBombsCount();

        JButton button = new JButton(" ");
        button.setActionCommand("New Game");
        button.setBackground(Color.gray);

        toolsPanel.add(Box.createHorizontalGlue());
        toolsPanel.add(lBombs);
        toolsPanel.add(Box.createHorizontalGlue());
        toolsPanel.add(button);
        toolsPanel.add(Box.createHorizontalGlue());
        toolsPanel.setBorder(new EmptyBorder(3,1,1,1));
        return toolsPanel;
    }

    public void refreshBombsCount(){
        lBombs.setText(Integer.toString(Constants.bombCount - Constants.openedBomb));
    }

    private JPanel board(){
        JPanel boardPanel = new JPanel();
        return boardPanel;
    }

    public void setView(JPanel panel){
        GUIframe.setContentPane(panel);
        GUIframe.setVisible(true);
    }

    private GridBagConstraints set(GridBagConstraints c, int x, int y){
        c.gridx = x;
        c.gridy = y;
        c.weighty = 2.0;
        return c;
    }

    public Interface() {
        GUIframe = new JFrame("Minesweeper ver.2.0");
        GUIframe.setSize(new Dimension(220,180));
        GUIframe.setLocationRelativeTo(null);
        GUIframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

