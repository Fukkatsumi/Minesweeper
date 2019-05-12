package com.minesweeper;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Controller {
    private static Logger log = Logger.getLogger(Controller.class.getName());
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private Model model = new Model();
    private View view = new View();

    public void start() {
        while (true) {
            view.showMenu();
            try {
                switch (reader.readLine()) {
                    case "g":
                        model.newGame();
                        game();
                        break;
                    case "s":
                        settings();
                        break;
                    case "e":
                        reader.close();
                        System.exit(0);
                        break;
                    default:
                        view.showWarning();
                }
            } catch (IOException e) {
                log.warning(e.getMessage());
                System.exit(-1);
            }
        }
    }

    private void game() throws IOException {
        while (!model.isGameOver()) {
            view.showGame();
            view.showBoard(model.getBoard());
            view.showTurn();
            Point field = getCords();
            if (field == null) {
                continue;
            }
            view.showGameActions();
            switch (reader.readLine()) {
                case "o":
                    if (!model.visible(field)) {
                        model.explore(field);
                        Constants.turnNumber++;
                    } else {
                        view.showClicked();
                    }
                    break;
                case "f":
                    if (!model.visible(field)) {
                        model.flag(field);
                        Constants.turnNumber++;
                        if(model.isWinner()){
                            winner();
                        }
                    } else {
                        view.showFlagged();
                    }
                    break;
                case "c":
                    break;
                default:
                    view.showWarning();
            }
        }
        gameOver();
    }

    private Point getCords() throws IOException {
        try {
            view.showGetX();
            int x = Integer.parseInt(reader.readLine()) - 1;
            view.showGetY();
            int y = Integer.parseInt(reader.readLine()) - 1;
            if (x >= 0 && x <= Constants.boardSize && y >= 0 && y <= Constants.boardSize) {
                return new Point(x, y);
            }
            throw new NumberFormatException();
        } catch (NumberFormatException e) {
            view.showWarning();
        }
        return null;
    }

    private void gameOver() throws IOException {
        view.showBoard(model.getBoard());
        view.showGameOver();
        actions();
    }

    private void winner() throws IOException {
        view.showWin();
        actions();
    }

    private void actions() throws IOException {
        while (true) {
            switch (reader.readLine()) {
                case "e":
                    reader.close();
                    System.exit(0);
                    break;
                case "r":
                    model.newGame();
                    game();
                    break;
                case "m":
                    start();
                    break;
                default:
                    view.showWarning();
            }
        }
    }

    private void settings() throws IOException {
        boolean flag = false;
        while (!flag) {
            view.showSettings();
            switch (reader.readLine()) {
                case "s":
                    view.showBoardSizeInput();
                    checkNumber(reader.readLine(), true);
                    break;
                case "c":
                    view.showBombCountInput();
                    checkNumber(reader.readLine(), false);
                    break;
                case "q":
                    flag = true;
                    break;
                default:
                    view.showWarning();
            }
        }
    }

    private void checkNumber(String data, boolean isBoardSize) throws IOException{
        try {
            int number = Integer.parseInt(data);
            if(isBoardSize){
                Constants.setBoardSize(number);
            } else {
                Constants.setBombCount(number);
            }
        } catch (NumberFormatException e){
            view.showWarning();
        }
    }
}
