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

    public void start(){
        while (true) {
            view.showMenu();
            try {
                switch (reader.readLine()) {
                    case "s":
                        game();
                        break;
                    case "c":
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

    private void game(){
        while (!model.isGameOver()) {
            view.showGame();
            view.showBoard(model.getBoard());
            try {
                view.showTurn();
                view.showGetX();
                int x = Integer.parseInt(reader.readLine());
                view.showGetY();
                int y = Integer.parseInt(reader.readLine());
                view.showActions();
                switch (reader.readLine()){
                    case "o":
                        Point p = new Point(x,y);
                        if(!model.clicked(p)){
                            model.open(p);
                        }else {
                            view.showClicked();
                        }
                        break;
                    case "f":
                        break;
                    case "c":
                        break;
                    default:
                        view.showWarning();
                }
            } catch (IOException e) {
                log.warning(e.getMessage());
                System.exit(-1);
            }
        }
        gameOver();
    }

    private void gameOver(){
        while (true) {
            view.showGameOver();
            try{
                switch (reader.readLine()){
                    case "e":
                        reader.close();
                        System.exit(0);
                        break;
                    case "r":
                        game();
                        break;
                    case "m":
                        start();
                        break;
                    default:
                        view.showWarning();
                }
            }catch (IOException e){
                log.warning(e.getMessage());
                System.exit(-1);
            }
        }
    }

    private void settings() {
        boolean flag = false;
        while (!flag) {
            try {
                view.showSettings();
                switch (reader.readLine()) {
                    case "s":
                        System.out.println("Input the board size:");
                        Constants.setBoardSize(Integer.parseInt(reader.readLine()));
                        break;
                    case "c":
                        System.out.println("Input the bomb count:");
                        Constants.setBombCount(Integer.parseInt(reader.readLine()));
                        break;
                    case "q":
                        flag = true;
                        break;
                    default:
                        view.showWarning();
                        flag = !flag;
                }
            } catch (IOException e) {
                log.warning(e.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    ======>Quit - 'q'
    ======>Restart - 'r'
    ======>New game - 'n'
        1 2 3 4 5
      -------------
    1 | _ 1 x 1 _ |
    2 | _ 1 2 2 _ |
    3 | _ 1 ^ 1 _ |
    4 | 1 2 1 1 1 |
    5 | x 1 _ 1 _ |
      -------------
    Turn N:
    Set cords:
    /input/
    To open field press 'o'
    To set flag press 'f'
    To cancel press 'c'
    /input/

     */
}
