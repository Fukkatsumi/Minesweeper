package com.minesweeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Controller {
    private static Logger log = Logger.getLogger(Controller.class.getName());
    private static Model model = new Model();
    public void start(){
        View.showMenu();
        setMode();
    }

    public void setMode() {
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                switch (reader.readLine()) {
                    case "s":
                        break;
                    case "c":
                        View.showSettings();
                        changeSettings();
                        break;
                    case "e":
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

    private void changeSettings() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = false;
        while (!flag) {
            flag = true;
            try {
                switch (reader.readLine()) {
                    case "f":
                        System.out.println("Input the field size:");
                        Settings.setFieldSize(Integer.parseInt(reader.readLine()));
                        break;
                    case "b":
                        System.out.println("Input the bomb count:");
                        Settings.setBombCount(Integer.parseInt(reader.readLine()));
                        break;
                    case "q":
                        break;
                    default:
                        System.out.println("Wrong parameter!");
                        flag = !flag;
                }
            } catch (IOException e) {
                log.warning(e.getMessage());
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
    To open press 'o'
    To set flag press 'f'
    To cancel press 'c'
    /input/

     */
}
