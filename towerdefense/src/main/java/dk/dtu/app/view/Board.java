package dk.dtu.app.view;

import dk.dtu.app.controller.*;
import javafx.scene.layout.GridPane;

public class Board {
    // Creating the player boards
    public static void createPlayerBoard(GridPane myBoard, int cellSize, int numOfCellsX, int numOfCellsY, int value) {
        
        MyButton[][] board = new MyButton[1400][900];
        for(int x = 0; x < numOfCellsX; x++){
            for(int y = 0; y < numOfCellsY; y++){
                // Create a new button which represents each cell on the board
                MyButton cell = new MyButton(value);
                cell.setPrefSize(cellSize, cellSize);
                // Add the button to the grid and a corresponding coordinate position
                myBoard.add(cell, x, y);
                board[x][y] = cell;

                board[x][y].setOnAction(e -> {
                    System.out.println("Button clicked");
                    System.out.println("Value: " + board[x][y].getValue());
                    System.out.println("X: " + x + " Y: " + y);
                    System.out.println("Cell size: " + cellSize);
                });
            }
        }
            }
        }
