package com.minesweeper.Console;

import java.util.Scanner;

public class SystemInputListener {
    public static void inputHandler(Source source) {
        switch (source) {
            case EXIT:
                exitHandler();
                break;
            case GAME_ACTION:
                gameActionHandler();
                break;
            case GAME_ENDING:
                gameEndingHandler();
                break;
            case MENU:
                menuHandler();
                break;
            case SETTINGS:
                settingsHandler();
                break;
        }
    }

    private static Scanner scanner = new Scanner(System.in);

    public static int numberInputHandler() {
        int in;
        if (!scanner.hasNextInt()) {
            resetScanner();
            return -1;
        }
        in = scanner.nextInt();
        return in;
    }

    private static ConsoleGame console = new ConsoleGame();

    private static void menuHandler() {
        char in;
        in = scanner.next().charAt(0);
        switch (in) {
            case 'n':
                console.game();
                break;
            case 's':
                console.settings();
                break;
            case 'e':
                console.exit();
                break;
        }
    }

    private static void settingsHandler() {
        char in;
        in = scanner.next().charAt(0);
        switch (in) {
            case 's':
                console.setupBoardSize();
                break;
            case 'c':
                console.setupBombsCount();
                break;
            case 'm':
                console.menu();
                break;
        }
    }

    private static void exitHandler() {
        char in;
        in = scanner.next().charAt(0);
        switch (in) {
            case 'n':
                console.menu();
                break;
            case 'y':
                scanner.close();
                System.exit(0);
                break;
        }
    }

    private static void gameEndingHandler() {
        if (scanner.hasNext()) {
            resetScanner();
            console.menu();
        }
    }

    private static void resetScanner() {
        scanner = new Scanner(System.in);
    }

    private static int gameAction;

    private static void gameActionHandler() {
        char in;
        in = scanner.next().charAt(0);
        switch (in) {
            case 'o':
                gameAction = 1;
                break;
            case 'f':
                gameAction = 0;
                break;
            default:
                gameAction = -1;
                break;
        }
    }

    public static int getGameAction() {
        return gameAction;
    }
}