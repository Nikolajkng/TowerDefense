package dk.dtu.app.controller;
import dk.dtu.app.view.MultiplayerBoard;

public class Tower {
    public static void placeTower(int x, int y) {


        System.out.println("Placing tower at: " + x + ", " + y);
        // Code here:
        // 1. Check if tower can be placed at x, y
        if(MultiplayerBoard.leftBoard[x][y].getValue() != -1){
            MultiplayerBoard.leftBoard[x][y].setText("X");

        }
        // 2. If yes, place tower
        // 3. If no, do nothing
    }

    
}
