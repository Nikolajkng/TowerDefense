package dk.dtu.app.view;

import dk.dtu.app.controller.*;
import javafx.scene.layout.GridPane;

public class Board {
    // Creating the player boards
    public static void createPlayerBoard(GridPane myBoard) {


        MyButton[][] board = new MyButton[1400][900];
        System.out.println(myBoard.getWidth());

        for(int x = 0; x < 400/20; x++){
            for(int y = 0; y < 400/20; y++){
                // Create a new button which represents each cell on the board
                MyButton cell = new MyButton(0);
                cell.setPrefSize(20, 20);
                // Add the button to the grid and a corresponding coordinate position
                myBoard.add(cell, x, y);
                board[x][y] = cell;
            }
        }
}
}
