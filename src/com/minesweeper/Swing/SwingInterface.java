package com.minesweeper.Swing;

import com.minesweeper.Field;
import com.minesweeper.View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class SwingInterface extends JFrame implements View {
    @Override
    public void menuInterface() {
        setView(pMenu());
    }

    @Override
    public void gameInterface() {

        setView(pGame());
    }

    @Override
    public void settingsInterface() {
        setView(pSettings());
    }

    private GridBagConstraints setConstraints(GridBagConstraints c, int x, int y) {
        c.gridx = x;
        c.gridy = y;
        c.weighty = 2.0;
        return c;
    }

    private JFrame GUIframe;

    public SwingInterface() {
        GUIframe = new JFrame("Minesweeper ver.2.0");
        GUIframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JDialog dialog;

    private void createDialog(String windowTitle, String buttonTitle, String messageText) {
        dialog = new JDialog(this, windowTitle, true);
        JPanel panel = new JPanel();
        panel.add(new JLabel(messageText));
        panel.add(button(buttonTitle));
        dialog.add(panel);
        dialog.setSize(100, 100);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    private JMenuBar mbGame;

    private void setView(JPanel panel) {
        if (dialog != null) {
            dialog.dispose();
        }
        GUIframe.setJMenuBar(mbGame);
        GUIframe.setContentPane(panel);
        GUIframe.setSize(panel.getSize());
        GUIframe.setLocationRelativeTo(null);
        GUIframe.setVisible(true);
    }

    private void setMenuBar() {
        mbGame = new JMenuBar();
        JMenu menu = new JMenu("Game");
        JMenuItem menuItem = new JMenuItem("Menu", KeyEvent.VK_M);
        menuItem.setActionCommand("Menu");
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

    private JPanel pMenu() {
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
        pMenu.setSize(new Dimension(220, 180));

        mbGame = null;

        return pMenu;
    }

    SwingActionListener actionListener;

    private JButton button(String title) {
        JButton button = new JButton(title);
        button.setActionCommand(title);
        button.addActionListener(actionListener);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(Color.CYAN.darker());
        return button;
    }

    private Map<Point, JButton> board;

    private JTextField boardSizeTF = new JTextField(3);
    private JTextField bombsCountTF = new JTextField(3);

    public String getNewBoardSize() {
        if (boardSizeTF == null) {
            return null;
        }
        return boardSizeTF.getText();
    }

    public String getNewBombsCount() {
        if (bombsCountTF == null) {
            return null;
        }
        return bombsCountTF.getText();
    }

    private JButton btnSetup;

    public void disableSetupButton() {
        btnSetup.setEnabled(false);
        btnSetup.setBackground(Color.GRAY.brighter());
    }

    private DocumentListener listener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            btnSetup.setEnabled(true);
            btnSetup.setBackground(Color.CYAN.darker());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            btnSetup.setEnabled(true);
            btnSetup.setBackground(Color.CYAN.darker());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    };

    private JPanel pSettings() {
        JPanel pSettings = new JPanel();
        pSettings.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel lBoardSize = new JLabel("Board size: ");
        lBoardSize.setForeground(Color.lightGray);

        JLabel lBombCount = new JLabel("Bombs count: ");
        lBombCount.setForeground(Color.lightGray);

        boardSizeTF.setBackground(Color.gray.brighter());
        boardSizeTF.getDocument().addDocumentListener(listener);

        bombsCountTF.setBackground(Color.gray.brighter());
        bombsCountTF.getDocument().addDocumentListener(listener);

        btnSetup = button("Setup");
        btnSetup.setEnabled(true);

        pSettings.add(lBoardSize, setConstraints(c, 0, 0));
        pSettings.add(lBombCount, setConstraints(c, 0, 1));
        pSettings.add(boardSizeTF, setConstraints(c, 1, 0));
        pSettings.add(bombsCountTF, setConstraints(c, 1, 1));
        pSettings.add(btnSetup, setConstraints(c, 0, 2));
        pSettings.add(button("Back"), setConstraints(c, 1, 2));

        pSettings.setBackground(Color.darkGray);
        pSettings.setSize(new Dimension(220, 180));

        mbGame = null;

        return pSettings;
    }

    private int boardSize;

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
        boardSizeTF.setText(Integer.toString(this.boardSize));
    }

    private JPanel pGame() {
        JPanel pGame = new JPanel();
        pGame.setLayout(new BoxLayout(pGame, BoxLayout.PAGE_AXIS));
        pGame.add(board());
        pGame.setSize(new Dimension((30 + 18) * boardSize, 15 + ((30 + 18) * boardSize)));
        setMenuBar();
        return pGame;
    }

    private JPanel board() {
        JPanel pBoard = new JPanel();
        pBoard.setLayout(new GridLayout(boardSize, boardSize));
        try {
            Point p = new Point();
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    p.setLocation(i, j);
                    JButton button = board.get(p);
                    button.setBackground(Color.gray);
                    pBoard.add(button);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        pBoard.setBackground(Color.darkGray);
        return pBoard;
    }

    private int bombsCount;

    public void setBombsCount(int bombsCount) {
        this.bombsCount = bombsCount;
        bombsCountTF.setText(Integer.toString(this.bombsCount));
    }

    public void refreshBoard(Map<Point, Field> board) {
        for (Point p : board.keySet()) {
            Field f = board.get(p);
            JButton b = this.board.get(p);
            if (f.isOpen()) {
                if (f.isBomb()) {
                    b.setBackground(Color.pink.darker());
                } else {
                    b.setBackground(Color.lightGray);
                }
                b.setEnabled(false);
                b.setUI(new MetalButtonUI() {
                    protected Color getDisabledTextColor() {
                        return Color.black;
                    }
                });
                b.setText(Character.toString(f.getTypeValue()));
            } else if (f.isMarked()) {
                b.setBackground(Color.cyan.darker());
                b.setText(Character.toString(f.getState().getChar()));
            } else if (!f.isMarked()) {
                b.setBackground(Color.gray);
                b.setText("");
            }
        }
    }

    private Point p = new Point();

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    private SwingGame controller;

    public void getController(SwingGame swing) {
        this.controller = swing;
    }

    public void setBoard(Map<Point, Field> board) {
        this.board = new HashMap<>();
        for (Point p : board.keySet()) {
            JButton button = new JButton();
            button.addMouseListener(new MouseListener() {
                private SwingGame s = controller;

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        s.openField();
                    }
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        s.markField();
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    setP(p);
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            button.setBackground(Color.GRAY);
            this.board.put(p, button);
        }
    }

    public void showVictoryDialog() {
        createDialog("Victory", "Go to menu", "You win!");
    }

    public void showLosingDialog() {
        createDialog("Losing", "Go to menu", "You lose!");
    }

    public void showWarningDialog() {
        createDialog("Warning", "OK", "Wrong input!!");
    }
}

