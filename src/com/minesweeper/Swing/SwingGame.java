package com.minesweeper.Swing;

import com.minesweeper.Board;
import com.minesweeper.Controller;

public class SwingGame extends Controller {
    private SwingInterface view = new SwingInterface();

    {
        view.actionListener = new SwingActionListener(this);
        view.getController(this);
    }

    @Override
    public void menu() {
        view.menuInterface();
    }

    @Override
    public void settings() {
        setDefaultParams();
        view.settingsInterface();
    }

    @Override
    public void game() {
        newGame();
        view.gameInterface();
    }

    private Board gameBoard = new Board();

    private void newGame() {
        gameBoard.fill();
        view.setBoard(gameBoard.getBoard());
        setDefaultParams();
    }

    private void setDefaultParams() {
        view.setBoardSize(gameBoard.getBoardSize());
        view.setBombsCount(gameBoard.getBombsCount());
    }

    public void openField() {
        gameBoard.explore(view.getP());
        view.refreshBoard(gameBoard.getBoard());
        checkLosing();
        checkVictory();
    }

    private void checkLosing() {
        if (gameBoard.isDetonatedAllBombs()) {
            view.showLosingDialog();
        }
    }

    public void markField() {
        gameBoard.defuseBomb(view.getP());
        view.refreshBoard(gameBoard.getBoard());
        checkVictory();
    }

    private void checkVictory() {
        if (gameBoard.isDefusedAllBombs() && gameBoard.isOpenedAllSafeArea()) {
            view.showVictoryDialog();
        }
    }

    public void setupSettings() {
        int oldBoardSize = gameBoard.getBoardSize();
        int oldBombsCount = gameBoard.getBombsCount();

        if (correctInput(view.getNewBoardSize()) && correctInput(view.getNewBombsCount())) {
            int newBoardSize = Integer.valueOf(view.getNewBoardSize());
            int newBombsCount = Integer.valueOf(view.getNewBombsCount());

            gameBoard.setBoardSize(newBoardSize);
            gameBoard.setBombsCount(newBombsCount);

            int actualBoardSize = gameBoard.getBoardSize();
            int actualBombsCount = gameBoard.getBombsCount();

            if (newBoardSize != actualBoardSize || newBombsCount != actualBombsCount) {
                view.showWarningDialog();
            }
            view.setBoardSize(actualBoardSize);
            view.setBombsCount(actualBombsCount);
        } else {
            view.setBoardSize(oldBoardSize);
            view.setBombsCount(oldBombsCount);
            view.showWarningDialog();
        }
    }

    private boolean correctInput(String s) {
        String regex = "\\d+";
        String input = s;
        return input.matches(regex);
    }
}
