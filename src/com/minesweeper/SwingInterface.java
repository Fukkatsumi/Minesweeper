package com.minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class SwingInterface extends JFrame implements Interface{
    private JFrame GUIframe;
    private JLabel lBombs;
    private JLabel lTimer;
    private JMenuBar mbGame;
    private static UIActionListener actionListener;

    {
        actionListener = new UIActionListener();
    }

    private Map<Point, JButton> board;

    public JPanel pMenu(){
        JPanel pMenu = new JPanel();
        pMenu.setLayout(new BoxLayout(pMenu, BoxLayout.PAGE_AXIS));
        pMenu.add(Box.createVerticalGlue());
        pMenu.add(button("New Game"));
        pMenu.add(Box.createVerticalGlue());
        pMenu.add(button("Settings"));
        pMenu.add(Box.createVerticalGlue());
        pMenu.add(button("Exit"));
        pMenu.add(Box.createVerticalGlue());

        pMenu.setBackground(Color.darkGray);
        pMenu.setSize(new Dimension(220,180));

        mbGame = null;

        return pMenu;
    }

    public JPanel pSettings(){
        JPanel pSettings = new JPanel();
        pSettings.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel lBoardSize = new JLabel("Board size: ");
        lBoardSize.setForeground(Color.lightGray);

        JLabel lBombCount = new JLabel("Bombs count: ");
        lBombCount.setForeground(Color.lightGray);

        JTextField boardSizeTF = new JTextField(3);
        boardSizeTF.setText(Integer.toString(Constants.boardSize));
        boardSizeTF.setBackground(Color.gray.brighter());

        JTextField bombCountTF = new JTextField(3);
        bombCountTF.setText(Integer.toString(Constants.bombCount));
        bombCountTF.setBackground(Color.gray.brighter());

        pSettings.add(lBoardSize, setConstraints(c,0,0));
        pSettings.add(lBombCount, setConstraints(c,0,1));
        pSettings.add(boardSizeTF, setConstraints(c,1,0));
        pSettings.add(bombCountTF, setConstraints(c,1,1));
        pSettings.add(button("OK"), setConstraints(c,0,2));
        pSettings.add(button("Cancel"), setConstraints(c,1,2));

        pSettings.setBackground(Color.darkGray);
        pSettings.setSize(new Dimension(220,180));

        mbGame = null;

        return pSettings;
    }

    public JPanel pGame(){
        JPanel pGame = new JPanel();
        pGame.setLayout(new BoxLayout(pGame, BoxLayout.PAGE_AXIS));
        pGame.add(tools());
        pGame.add(board());
        pGame.setSize(new Dimension(20 + 18*Constants.boardSize, 55 + 18*Constants.boardSize));
        setMenuBar();
        return pGame;
    }

    private GridBagConstraints setConstraints(GridBagConstraints c, int x, int y){
        c.gridx = x;
        c.gridy = y;
        c.weighty = 2.0;
        return c;
    }

    private void setMenuBar(){
        mbGame = new JMenuBar();
        JMenu menu = new JMenu("Game");
        JMenuItem menuItem = new JMenuItem("Quit to pMenu", KeyEvent.VK_Q);
        menuItem.setActionCommand("Quit to pMenu");
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
        menuItem = new JMenuItem("New Game", KeyEvent.VK_N);
        menuItem.setActionCommand("New Game");
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        menuItem.setActionCommand("Exit");
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
        mbGame.add(menu);
    }

    private JButton button(String title){
        JButton button = new JButton(title);
        button.setActionCommand(title);
        button.addActionListener(actionListener);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(Color.CYAN.darker());
        return button;
    }

    private JPanel tools(){
        JPanel pTools = new JPanel();
        pTools.setLayout(new BoxLayout(pTools, BoxLayout.LINE_AXIS));

        lBombs = new JLabel();
        lBombs.setForeground(Color.WHITE);
        refreshBombsCount();

        JButton button = new JButton(" ");
        button.setActionCommand("New Game");
        button.setFocusPainted(false);
        button.addActionListener(actionListener);
        button.setBackground(Color.gray);

        lTimer = new JLabel("00:00");
        lTimer.setForeground(Color.WHITE);

        pTools.add(Box.createHorizontalGlue());
        pTools.add(lBombs);
        pTools.add(Box.createHorizontalGlue());
        pTools.add(button);
        pTools.add(Box.createHorizontalGlue());
        pTools.add(lTimer);
        pTools.add(Box.createHorizontalGlue());
        pTools.setBorder(new EmptyBorder(3,1,1,1));
        pTools.setBackground(Color.darkGray);
        return pTools;
    }

    private JPanel board(){
        JPanel pBoard = new JPanel();
        pBoard.setLayout(new GridLayout(Constants.boardSize, Constants.boardSize));
        try {
            for (JButton button : board.values()) {
                pBoard.add(button);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        pBoard.setBackground(Color.darkGray);
        return pBoard;
    }

    public void refreshBombsCount(){
        lBombs.setText(Integer.toString(Constants.bombCount - Constants.openedBomb));
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
        GUIframe.setJMenuBar(mbGame);
        GUIframe.setContentPane(panel);
        GUIframe.setSize(panel.getSize());
        GUIframe.setVisible(true);
    }

    public SwingInterface() {
        GUIframe = new JFrame("Minesweeper ver.2.0");
        GUIframe.setLocationRelativeTo(null);
        GUIframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void menu() {

    }

    @Override
    public void game() {

    }

    @Override
    public void settings() {

    }
}

