package dk.dtu.app.controller;


public class Tower {
    public static void placeTower(int x, int y, MyButton[][] board, String action) {

        System.out.println("Placing tower at: (" + x + ", " + y+") with action: " + action);
        // Check if tower can be placed at x, y
        if(true){
            if(action == "tower1"){
                board[x][y].setText("X");
                board[x][y].setValue(-1);
            };
            if(action == "tower2"){
                board[x][y].setText("O");
                board[x][y].setValue(-1); 
            };
            if(action == "tower3"){
                board[x][y].setText("±");
                board[x][y].setValue(-1); 
            };

        } else {
            System.out.println("Tower cannot be placed here");
        }
    }   
}