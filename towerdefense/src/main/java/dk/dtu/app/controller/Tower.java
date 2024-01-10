package dk.dtu.app.controller;
import dk.dtu.app.view.Board;

public class Tower {
    public static void placeTower(int x, int y, MyButton[][] board) {
        Board.action = "placeTower";

        System.out.println("Placing tower at: " + x + ", " + y);
        // Code here:
        // 1. Check if tower can be placed at x, y
        if(board[x][y].getValue() != -1){
            board[x][y].setText("X");
            

        }
        // 2. If yes, place tower
        // 3. If no, do nothing
    }

    
}
