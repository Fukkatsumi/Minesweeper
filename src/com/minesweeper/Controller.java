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
                        turn();
                        break;
                    case "c":
                        changeSettings();
                        break;
                    case "e":
                        reader.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong mode!");
                }
            } catch (IOException e) {
                log.warning(e.getMessage());
            }
        }
    }

    private void turn(){
        while (!model.isGameOver()) {
            view.showGame();
            System.out.println("Turn " + Constants.turnNumber + ":\n" +
                    "Set cords:" +
                    "x = ");
            try {
                int x = Integer.parseInt(reader.readLine());
                System.out.println("y = ");
                int y = Integer.parseInt(reader.readLine());
                System.out.println("    To open field press 'o'\n" +
                        "    To set flag press 'f'\n" +
                        "    To cancel press 'c'\n ");
                switch (reader.readLine()){
                    case "o":
                        Point p = new Point(x,y);
                        if(!model.clicked(p)){
                            model.open(p);
                        }
                        break;
                    case "f":
                        break;
                    case "c":
                        break;
                    default:
                        System.out.println("Wrong parameter!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void changeSettings() {
        boolean flag = false;
        while (!flag) {
            try {
                view.showSettings();
                switch (reader.readLine()) {
                    case "f":
                        System.out.println("Input the field size:");
                        Constants.setFieldSize(Integer.parseInt(reader.readLine()));
                        break;
                    case "b":
                        System.out.println("Input the bomb count:");
                        Constants.setBombCount(Integer.parseInt(reader.readLine()));
                        break;
                    case "q":
                        flag = true;
                        break;
                    default:
                        System.out.println("Wrong parameter!");
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
