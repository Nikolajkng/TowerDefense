package dk.dtu.app.view;

import dk.dtu.app.controller.*;
import javafx.scene.layout.GridPane;

public class Board {
    // Creating the player boards
    public static void createPlayerBoard(GridPane myBoard, int cellSize, int numOfCellsX, int numOfCellsY) {
        
        MyButton[][] board = new MyButton[1400][900];
        for(int x = 0; x < numOfCellsX; x++){
            for(int y = 0; y < numOfCellsY; y++){
                // Create a new button which represents each cell on the board
                MyButton cell = new MyButton(0);
                cell.setPrefSize(cellSize, cellSize);
                // Add the button to the grid and a corresponding coordinate position
                myBoard.add(cell, x, y);
                board[x][y] = cell;
            }
        }
}
}
