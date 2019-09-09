package com.minesweeper.Console;

import com.minesweeper.Board;
import com.minesweeper.Controller;
import com.minesweeper.Field;

import java.awt.*;


public class ConsoleGame extends Controller {
    private ConsoleInterface view = new ConsoleInterface();

    @Override
    public void menu() {
        while (true) {
            view.menuInterface();
            SystemInputListener.inputHandler(Source.MENU);
        }
    }

    @Override
    public void settings() {
        while (true) {
            view.settingsInterface();
            SystemInputListener.inputHandler(Source.SETTINGS);
        }
    }

    private Board gameBoard = new Board();

    @Override
    public void game() {
        newGame();
        while (true) {
            view.setBoard(gameBoard.getBoard());
            view.gameInterface();
            if (gameBoard.isDetonatedAllBombs()) {
                losing();
            } else if (gameBoard.isDefusedAllBombs()) {
                victory();
            } else {
                turn();
            }
        }
    }

    private void newGame() {
        gameBoard.fill();
        view.getBoardSizeToShow(gameBoard.getBoardSize());
        view.getBombsCountToShow(gameBoard.getBombsCount());
    }

    private void losing() {
        view.showGameOver();
        SystemInputListener.inputHandler(Source.GAME_ENDING);
    }

    private void victory() {
        view.showVictory();
        SystemInputListener.inputHandler(Source.GAME_ENDING);
    }

    private void turn() {
        Point currentPoint = setPointFromConsole();
        if (correctRange(currentPoint)) {
            view.showGameActions();
            SystemInputListener.inputHandler(Source.GAME_ACTION);
            int action = SystemInputListener.getGameAction();
            if (action == 1) {
                openField(currentPoint);
            } else if (action == 0) {
                markField(currentPoint);
            } else {
                view.showError();
            }
        } else {
            view.showError();
        }
    }

    private Point setPointFromConsole() {
        view.showGetX();
        int inputNumber = SystemInputListener.numberInputHandler();
        Point p = new Point();
        p.y = inputNumber - 1;
        view.showGetY();
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
            view.showCantMarkField();
        }
        if (field.isMarked()) {
            view.showCantOpenField();
        } else {
            gameBoard.explore(p);
        }
    }

    private void markField(Point p) {
        if (gameBoard.getBoard().get(p).isOpen()) {
            view.showCantMarkField();
        }
        gameBoard.defuseBomb(p);
    }

    public void setupBombsCount() {
        view.showBombCountInput();
        int inputNumber = SystemInputListener.numberInputHandler();
        if (inputNumber == -1) {
            view.showError();
        } else {
            gameBoard.setBombsCount(inputNumber);
            view.getBombsCountToShow(gameBoard.getBombsCount());
            view.showActualBombsCount();
        }
    }

    public void setupBoardSize() {
        view.showBoardSizeInput();
        int inputNumber = SystemInputListener.numberInputHandler();
        if (inputNumber == -1) {
            view.showError();
        } else {
            gameBoard.setBoardSize(inputNumber);
            view.getBoardSizeToShow(gameBoard.getBoardSize());
            view.showActualBoardSize();
        }
    }

    public void exit() {
        while (true) {
            view.showExit();
            SystemInputListener.inputHandler(Source.EXIT);
        }
    }
}
