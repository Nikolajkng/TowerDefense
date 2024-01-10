package dk.dtu.app.controller;

import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;

public class ActionHandler {

    public static void selectAction(Object[] actioninfo, MyButton[][] board) {
        System.out.println("I am inside ActionHandler");
        int x = (int) actioninfo[0];
        int y = (int) actioninfo[1];
        String action = (String) actioninfo[2];
        System.out.println("-----------");
        System.out.println(x);
        System.out.println(y);
        System.out.println(action);
        System.out.println("-----------");


        switch (action) {
            case "tower1":
                board[x][y].setText("X");
                board[x][y].setValue(-1);
                break;
            case "tower2":
                board[x][y].setText("0");
                board[x][y].setValue(-1);
                break;
            case "tower3":
                board[x][y].setText("±");
                board[x][y].setValue(-1);
                break;
        }

    }
}
