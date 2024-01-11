package dk.dtu.app.controller;

public class Tower {

    public enum ActionType {
        NONE,
        TOWER1,
        TOWER2,
        TOWER3,
    }

    public static void placeTower(int x, int y, MyButton[][] board, Tower.ActionType type) {

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