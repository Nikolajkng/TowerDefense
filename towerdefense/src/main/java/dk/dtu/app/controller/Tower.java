package dk.dtu.app.controller;

import dk.dtu.app.controller.Action.ActionType;

public class Tower {



    public static void placeTower(int x, int y, MyButton[][] board, Action.ActionType type) {

        System.out.println("Placing tower at: (" + x + ", " + y + ") with action: " + type);

        if (type == ActionType.TOWER1) {
            board[x][y].setText("X");
            board[x][y].setValue(-1);
        }
        else
        if (type == ActionType.TOWER2) {
            board[x][y].setText("O");
            board[x][y].setValue(-1);
        }
        else
        if (type == ActionType.TOWER3) {
            board[x][y].setText("±");
            board[x][y].setValue(-1);
        } else {
            System.out.println("No tower selected");
        }

    }
}