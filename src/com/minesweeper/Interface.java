package com.minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Interface {
    private JFrame GUIframe;
    private JLabel lBombs;
    private JLabel lTimer;

    private Map<Point, JButton> board;

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
        menuPanel.setSize(new Dimension(220,180));
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
        settingsPanel.setSize(new Dimension(220,180));
        return settingsPanel;
    }

    public JPanel game(){
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));
        gamePanel.add(tools());
        gamePanel.add(board());
        gamePanel.setSize(new Dimension(20 + 18*Constants.boardSize, 55 + 18*Constants.boardSize));
        return gamePanel;
    }

    private JPanel tools(){
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.LINE_AXIS));

        lBombs = new JLabel();
        refreshBombsCount();

        JButton button = new JButton(" ");
        button.setActionCommand("New Game");
        button.setFocusPainted(false);
        //button.setIcon();
        button.setBackground(Color.gray);

        lTimer = new JLabel("00:00");

        toolsPanel.add(Box.createHorizontalGlue());
        toolsPanel.add(lBombs);
        toolsPanel.add(Box.createHorizontalGlue());
        toolsPanel.add(button);
        toolsPanel.add(Box.createHorizontalGlue());
        toolsPanel.add(lTimer);
        toolsPanel.add(Box.createHorizontalGlue());
        toolsPanel.setBorder(new EmptyBorder(3,1,1,1));
        return toolsPanel;
    }

    public void refreshBombsCount(){
        lBombs.setText(Integer.toString(Constants.bombCount - Constants.openedBomb));
    }

    private JPanel board(){
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(Constants.boardSize, Constants.boardSize));
        try {
            for (JButton button : board.values()) {
                boardPanel.add(button);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return boardPanel;
    }

    public void setBoard(Map<Point, Field> board){
        this.board = new HashMap<>();
        for (Point p: board.keySet()){
            this.board.put(p, new JButton());
            this.board.get(p).addMouseListener(null);
            this.board.get(p).setBackground(Color.BLUE.darker());
        }
    }

    public void setView(JPanel panel){
        GUIframe.setContentPane(panel);
        GUIframe.setSize(panel.getSize());
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
        GUIframe.setLocationRelativeTo(null);
        GUIframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

