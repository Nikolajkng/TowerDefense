package dk.dtu.app.controller;

public class Tower {
    public static void placeTower(int x, int y, MyButton[][] board, String action) {

        System.out.println("Placing tower at: (" + x + ", " + y + ") with action: " + action);

        if (action == "tower1") {
            board[x][y].setText("X");
            board[x][y].setValue(-1);
        } else if (action == "tower2") {
            board[x][y].setText("O");
            board[x][y].setValue(-1);
        } else if (action == "tower3") {
            board[x][y].setText("±");
            board[x][y].setValue(-1);
        } else {
            System.out.println("Action does not match anything");
        }

    }
}