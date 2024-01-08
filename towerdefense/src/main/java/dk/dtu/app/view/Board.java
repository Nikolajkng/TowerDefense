package dk.dtu.app.view;

import dk.dtu.app.controller.*;
import javafx.scene.layout.GridPane;

public class Board {
    // Creating the player boards
    public static void createPlayerBoard(GridPane myBoard, int cellSize) {
        
        MyButton[][] board = new MyButton[1400][900];
        int numOfCells = 10;
        int buttonSize = cellSize;

        for(int x = 0; x < numOfCells+4; x++){
            for(int y = 0; y < numOfCells; y++){
                // Create a new button which represents each cell on the board
                MyButton cell = new MyButton(0);
                cell.setPrefSize(buttonSize, buttonSize);
                // Add the button to the grid and a corresponding coordinate position
                myBoard.add(cell, x, y);
                board[x][y] = cell;
            }
        }
}
}
