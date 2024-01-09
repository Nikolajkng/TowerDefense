package dk.dtu.app.view;

import dk.dtu.app.controller.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

public class Board {
    // Creating the player boards
    public static void createPlayerBoard(GridPane myBoard, int cellSize, int numOfCellsX, int numOfCellsY, int value) {

        MyButton[][] board = new MyButton[1400][900];
        int x = 0;
        int y = 0;
        for (x = 0; x < numOfCellsX; x++) {
            for (y = 0; y < numOfCellsY; y++) {
                // Create a new button which represents each cell on the board
                MyButton cell = new MyButton(value);
                cell.setPrefSize(cellSize, cellSize);
                // Add the button to the grid and a corresponding coordinate position
                myBoard.add(cell, x, y);
                board[x][y] = cell;

                final int finalX = x;
                final int finalY = y;
                board[x][y].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        clickInfo(finalX, finalY);
                    }
                });
            }
        }
    }

    private static void clickInfo(int x, int y) {
        System.out.println("Button clicked");
        System.out.println("X: " + x + " Y: " + y);
    }
}
