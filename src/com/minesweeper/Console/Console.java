package com.minesweeper.Console;

import com.minesweeper.Board;
import com.minesweeper.Controller;
import com.minesweeper.Field;

import java.awt.*;


public class Console extends ConsoleInterface implements Controller {
    @Override
    public void menu() {
        while (true) {
            menuInterface();
            SystemInputListener.inputHandler(Source.MENU);
        }
    }

    @Override
    public void settings() {
        while (true) {
            settingsInterface();
            SystemInputListener.inputHandler(Source.SETTINGS);
        }
    }

    private Board gameBoard = new Board();

    @Override
    public void game() {
        newGame();
        while (true) {
            setBoard(gameBoard.getBoard());
            gameInterface();
            if (gameBoard.isDetonatedAllBombs()) {
                lose();
            } else if (gameBoard.isDefusedAllBombs()) {
                win();
            } else {
                turn();
            }
        }
    }

    private void newGame() {
        gameBoard.fill();
        getBoardSizeToShow(gameBoard.getBoardSize());
        getBombsCountToShow(gameBoard.getBombsCount());
    }

    private void lose() {
        showGameOver();
        SystemInputListener.inputHandler(Source.GAME_ENDING);
    }

    private void win() {
        showWin();
        SystemInputListener.inputHandler(Source.GAME_ENDING);
    }

    private void turn() {
        Point currentPoint = setPointFromConsole();
        if (correctRange(currentPoint)) {
            showGameActions();
            SystemInputListener.inputHandler(Source.GAME_ACTION);
            int action = SystemInputListener.getGameAction();
            if (action == 1) {
                openField(currentPoint);
            } else if (action == 0) {
                markField(currentPoint);
            } else {
                showError();
            }
        } else {
            showError();
        }
    }

    private Point setPointFromConsole() {
        showGetX();
        int inputNumber = SystemInputListener.numberInputHandler();
        Point p = new Point();
        p.y = inputNumber - 1;
        showGetY();
        inputNumber = SystemInputListener.numberInputHandler();
        p.x = inputNumber - 1;
        return p;
    }

    private boolean correctRange(Point p) {
        return p.x >= 0 && p.y >= 0 && p.x <= gameBoard.getBoardSize() && p.y <= gameBoard.getBoardSize();
    }

    private void openField(Point p) {
        Field field = gameBoard.getBoard().get(p);
        if (field.isOpen()) {
            showCantMarkField();
        }
        if (field.isMarked()) {
            showCantOpenField();
        } else {
            gameBoard.explore(p);
        }
    }

    private void markField(Point p) {
        if (gameBoard.getBoard().get(p).isOpen()) {
            showCantMarkField();
        }
        gameBoard.defuseBomb(p);
    }

    public void setupBombsCount() {
        showBombCountInput();
        int inputNumber = SystemInputListener.numberInputHandler();
        if (inputNumber == -1) {
            showError();
        } else {
            gameBoard.setBombsCount(inputNumber);
            getBombsCountToShow(gameBoard.getBombsCount());
            showActualBombsCount();
        }
    }

    public void setupBoardSize() {
        showBoardSizeInput();
        int inputNumber = SystemInputListener.numberInputHandler();
        if (inputNumber == -1) {
            showError();
        } else {
            gameBoard.setBoardSize(inputNumber);
            getBoardSizeToShow(gameBoard.getBoardSize());
            showActualBoardSize();
        }
    }

    public void exit() {
        while (true) {
            showExit();
            SystemInputListener.inputHandler(Source.EXIT);
        }
    }
}
